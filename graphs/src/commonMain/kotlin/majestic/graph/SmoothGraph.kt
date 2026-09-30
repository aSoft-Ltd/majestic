package majestic.graph

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun SmoothGraph(
    color: Color,
    points: List<Float>,
    heightScale: Float = 1f,
    strokeWidth: Dp = 2.dp,
    strokeCap: StrokeCap = StrokeCap.Round,
    fillBrush: Brush? = Brush.verticalGradient(listOf(color.copy(alpha = .5f), Color.Transparent)),
    modifier: Modifier = Modifier
) = Canvas(modifier = modifier) {
    if (points.size < 2) return@Canvas
    val maxValue = points.maxOrNull()?.takeUnless { it == 0f } ?: 1f
    val widthStep = size.width / (points.size - 1)
    val coordinates = points.mapIndexed { index, value ->
        Offset(index * widthStep, size.height * (1f - (value / maxValue) * heightScale))
    }
    val curve = Path().apply {
        moveTo(coordinates.first().x, coordinates.first().y)
        for (index in 1 until coordinates.size) {
            val previous = coordinates[index - 1]
            val current = coordinates[index]
            val centerX = previous.x + (current.x - previous.x) / 2
            cubicTo(centerX, previous.y, centerX, current.y, current.x, current.y)
        }
    }

    fillBrush?.let { brush ->
        val fill = Path().apply {
            addPath(curve)
            lineTo(size.width, size.height)
            lineTo(0f, size.height)
            close()
        }
        drawPath(path = fill, brush = brush, style = Fill)
    }
    drawPath(
        path = curve,
        color = color,
        style = Stroke(width = strokeWidth.toPx(), cap = strokeCap)
    )
}
