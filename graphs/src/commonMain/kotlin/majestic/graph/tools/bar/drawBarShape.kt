package majestic.graph.tools.bar

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import kotlin.math.min

internal fun DrawScope.drawBarShape(
    shape: BarShape,
    color: Color,
    topLeft: Offset,
    size: Size
) {
    when (shape) {
        is Rectangle -> {
            drawRect(
                color = color,
                topLeft = topLeft,
                size = size
            )
        }

        is RoundedRectangleShape -> {
            drawRectangle(
                color = color,
                topLeft = topLeft,
                size = size,
                cornerShape = shape
            )
        }

        is Circle -> {
            val radius = min(size.width, size.height) / 2
            drawCircle(
                color = color,
                radius = radius,
                center = Offset(
                    topLeft.x + size.width / 2,
                    topLeft.y + size.height / 2
                )
            )
        }

        is Diamond -> {
            val centerX = topLeft.x + size.width / 2
            val centerY = topLeft.y + size.height / 2

            val path = Path().apply {
                moveTo(centerX, topLeft.y)
                lineTo(topLeft.x + size.width, centerY)
                lineTo(centerX, topLeft.y + size.height)
                lineTo(topLeft.x, centerY)
                close()
            }

            drawPath(
                path = path,
                color = color
            )
        }
    }
}
