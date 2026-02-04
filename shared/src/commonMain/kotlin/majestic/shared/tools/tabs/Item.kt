package majestic.shared.tools.tabs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.ColorPair

internal fun Modifier.item(selected: Boolean, isHovered: Boolean, colors: TabItemColors) = this
    .fillMaxHeight()
    .then(
        if (selected || isHovered) Modifier.drawBehind {
            val strokeWidth = 3.dp.toPx()
            drawLine(
                color = when (selected) {
                    true -> colors.underline.background
                    false -> colors.underline.foreground.copy(0.2f)
                },
                start = Offset(0f, size.height - strokeWidth / 2),
                end = Offset(size.width, size.height - strokeWidth / 2),
                strokeWidth = strokeWidth
            )
        } else Modifier
    )

data class TextColors(
    val selected: Color,
    val isHovered: Color,
    val unselected: Color
)

data class TabItemColors(
    val text: TextColors,
    val underline: ColorPair
)

@Composable
internal fun Item(
    modifier: Modifier,
    colors: TabItemColors,
    isHovered: Boolean,
    label: String,
    selected: Boolean = false,
) {
    val textColor = when {
        selected -> colors.text.selected
        isHovered -> colors.text.isHovered
        else -> colors.text.unselected
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 10.dp),
            text = label,
            fontSize = 14.sp,
            color = textColor
        )
    }
}