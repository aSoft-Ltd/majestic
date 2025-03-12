package majestic

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import majestic.colors.ColorPair
import majestic.tooling.onClick

enum class ExpandDirection {
    UP, DOWN, LEFT, RIGHT
}

@Composable
private fun Label(color: ColorPair, rotation: Float = 0f) = Icon(
    modifier = Modifier.size(26.dp).graphicsLayer { rotationZ = rotation },
    imageVector = Icons.Default.Add,
    contentDescription = null,
    tint = color.foreground
)

fun Modifier.floatActionButton(
    background: Color,
    onClick: () -> Unit
) = this.size(56.dp)
    .clip(CircleShape)
    .background(background)
    .onClick(onClick)

@Composable
fun FloatingActionButton(
    expanded: Boolean,
    onExpandChanged: (Boolean) -> Unit,
    direction: ExpandDirection,
    color: ColorPair = ColorPair(
        background = Color.Black,
        foreground = Color.White
    ),
    modifier: Modifier = Modifier.floatActionButton(color.background) {
        onExpandChanged(!expanded)
    },
    label: @Composable (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    val rotation by animateFloatAsState(if (expanded) 45f else 0f)
    Box(contentAlignment = Alignment.Center) {
        when (direction) {
            ExpandDirection.UP -> Column(horizontalAlignment = Alignment.End) {
                if (expanded) content()
                Box(
                    modifier = modifier,
                    contentAlignment = Alignment.Center
                ) {
                    if (label != null) label() else Label(color, rotation)
                }
            }

            ExpandDirection.DOWN -> Column(horizontalAlignment = Alignment.End) {
                Box(
                    modifier = modifier,
                    contentAlignment = Alignment.Center
                ) {
                    if (label != null) label() else Label(color, rotation)
                }
                if (expanded) content()
            }

            ExpandDirection.LEFT -> Row(verticalAlignment = Alignment.CenterVertically) {
                if (expanded) content()
                Box(
                    modifier = modifier,
                    contentAlignment = Alignment.Center
                ) {
                    if (label != null) label() else Label(color, rotation)
                }
            }

            ExpandDirection.RIGHT -> Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = modifier,
                    contentAlignment = Alignment.Center
                ) {
                    if (label != null) label() else Label(color, rotation)
                }
                if (expanded) content()
            }
        }
    }
}
