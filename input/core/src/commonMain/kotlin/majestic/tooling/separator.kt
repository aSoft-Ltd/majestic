package majestic.tooling

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.separator(
    color: Color,
    width: Dp = 1.dp,
    isLast: Boolean = false,
    direction: DrawDirection = DrawDirection.BOTTOM
) = drawBehind {
    val stroke = width.toPx()

    when (direction) {
        DrawDirection.TOP -> drawLine(
            color = color,
            start = Offset(0f, 0f),
            end = Offset(size.width, 0f),
            strokeWidth = stroke
        )

        DrawDirection.BOTTOM -> drawLine(
            color = if (isLast) Color.Transparent else color,
            start = Offset(0f, size.height + stroke / 2),
            end = Offset(size.width, size.height + stroke / 2),
            strokeWidth = stroke
        )

        DrawDirection.RIGHT -> drawLine(
            color = color,
            start = Offset(size.width - stroke / 2, 0f),
            end = Offset(size.width - stroke / 2, size.height),
            strokeWidth = width.toPx(),
        )
    }
}
