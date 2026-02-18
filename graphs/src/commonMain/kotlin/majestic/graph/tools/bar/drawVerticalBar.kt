package majestic.graph.tools.bar

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope

internal fun DrawScope.drawVerticalBar(
    width: Float,
    height: Float,
    value: Float,
    color: Color,
    shape: BarShape,
    animation: Float,
) {
    val barHeight = height * (value / 100f) * animation
    val barTop = size.height - barHeight

    drawBarShape(
        shape = shape,
        color = color,
        topLeft = Offset(0f, barTop),
        size = Size(width, barHeight)
    )
}
