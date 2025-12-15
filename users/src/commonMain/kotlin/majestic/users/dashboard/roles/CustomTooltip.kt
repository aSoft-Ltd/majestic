package majestic.users.dashboard.roles

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup

/**
 * Custom tooltip that automatically positions itself based on available space
 * and displays an arrow pointing to the anchor element.
 */

enum class TooltipPosition {
    Top, Bottom, Left, Right
}

data class TooltipPositionData(
    val position: TooltipPosition, val offset: IntOffset
)

@Composable
fun CustomTooltip(
    tooltipContent: @Composable () -> Unit, trigger: @Composable () -> Unit, modifier: Modifier = Modifier
) {
    var showTooltip by remember { mutableStateOf(false) }

    var anchorPosition by remember { mutableStateOf(Offset.Zero) }
    var anchorSize by remember { mutableStateOf(IntSize.Zero) }

    var tooltipSize by remember { mutableStateOf(IntSize.Zero) }

    val density = LocalDensity.current
    val windowInfo = LocalWindowInfo.current
    val screenWidth = windowInfo.containerSize.width
    val screenHeight = windowInfo.containerSize.height

    val tooltipPositionData by remember(anchorPosition, anchorSize, tooltipSize, screenWidth, screenHeight) {
        derivedStateOf {
            calculateBestPosition(
                anchorPosition = anchorPosition,
                anchorSize = anchorSize,
                tooltipSize = tooltipSize,
                screenWidth = screenWidth,
                screenHeight = screenHeight,
                density = density
            )
        }
    }

    Box(modifier = Modifier.clickable {
        showTooltip = true
    }.onGloballyPositioned { coordinates ->
        anchorPosition = coordinates.positionInRoot()
        anchorSize = coordinates.size
    }) {
        trigger()
    }

    if (showTooltip) {
        if (showTooltip) {
            Popup(
                onDismissRequest = {
                    showTooltip = false
                }) {
                Surface(
                    color = Color(0xFF1C1C1E),
                    shape = RoundedCornerShape(8.dp),
                    shadowElevation = 8.dp,
                    modifier = Modifier.offset {
                        tooltipPositionData.offset
                    }.onGloballyPositioned { coordinates -> tooltipSize = coordinates.size }) {
                    Box(modifier = Modifier.padding(16.dp)) {
                        tooltipContent()
                    }
                }
            }
        }
    }


}


// Arrow Components
@Composable
private fun ArrowDown(color: Color) {
    Canvas(modifier = Modifier.size(20.dp, 10.dp)) {
        val path = Path().apply {
            moveTo(size.width / 2, size.height)
            lineTo(0f, 0f)
            lineTo(size.width, 0f)
            close()
        }
        drawPath(path = path, color = color)
    }
}

@Composable
private fun ArrowUp(color: Color) {
    Canvas(modifier = Modifier.size(20.dp, 10.dp)) {
        val path = Path().apply {
            moveTo(size.width / 2, 0f)
            lineTo(0f, size.height)
            lineTo(size.width, size.height)
            close()
        }
        drawPath(path = path, color = color)
    }
}

@Composable
private fun ArrowLeft(color: Color) {
    Canvas(modifier = Modifier.size(10.dp, 20.dp)) {
        val path = Path().apply {
            moveTo(0f, size.height / 2)
            lineTo(size.width, 0f)
            lineTo(size.width, size.height)
            close()
        }
        drawPath(path = path, color = color)
    }
}

@Composable
private fun ArrowRight(color: Color) {
    Canvas(modifier = Modifier.size(10.dp, 20.dp)) {
        val path = Path().apply {
            moveTo(size.width, size.height / 2)
            lineTo(0f, 0f)
            lineTo(0f, size.height)
            close()
        }
        drawPath(path = path, color = color)
    }
}

private fun calculateBestPosition(
    anchorPosition: Offset,
    anchorSize: IntSize,
    tooltipSize: IntSize,
    screenWidth: Int,
    screenHeight: Int,
    density: Density
): TooltipPositionData {
    val spacing = with(density) { 8.dp.toPx() }

    val anchorCenterX = anchorPosition.x + anchorSize.width / 2
    val anchorCenterY = anchorPosition.y + anchorSize.height / 2

    val spaceAbove = anchorPosition.y
    val spaceBelow = screenHeight - (anchorPosition.y + anchorSize.height)
    val spaceLeft = anchorPosition.x
    val spaceRight = screenWidth - (anchorPosition.x + anchorSize.width)

    val position = when {
        spaceAbove >= tooltipSize.height + spacing -> TooltipPosition.Top
        spaceBelow >= tooltipSize.height + spacing -> TooltipPosition.Bottom
        spaceLeft >= tooltipSize.width + spacing -> TooltipPosition.Left
        spaceRight >= tooltipSize.width + spacing -> TooltipPosition.Right
        else -> TooltipPosition.Top
    }

    val offset = when (position) {
        TooltipPosition.Top -> IntOffset(
            x = (anchorCenterX - tooltipSize.width / 2).toInt(),
            y = (anchorPosition.y - tooltipSize.height - spacing).toInt()
        )

        TooltipPosition.Bottom -> IntOffset(
            x = (anchorCenterX - tooltipSize.width / 2).toInt(),
            y = (anchorPosition.y + anchorSize.height + spacing).toInt()
        )

        TooltipPosition.Left -> IntOffset(
            x = (anchorPosition.x - tooltipSize.width - spacing).toInt(),
            y = (anchorCenterY - tooltipSize.height / 2).toInt()
        )

        TooltipPosition.Right -> IntOffset(
            x = (anchorPosition.x + anchorSize.width + spacing).toInt(),
            y = (anchorCenterY - tooltipSize.height / 2).toInt()
        )
    }

    return TooltipPositionData(position, offset)
}