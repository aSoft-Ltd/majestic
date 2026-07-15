package majestic.graph.line

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

fun DrawScope.drawTrendLine(
    points: List<LineTrendPoint>,
    minV: Float,
    maxV: Float,
    xAt: (Int) -> Float,
    plotTop: Float,
    plotH: Float,
    color: Color,
    pathEffect: PathEffect? = null,
    showPoints: Boolean = false,
    pointFill: Color? = null
) {
    if (points.size < 2) return

    fun pointOffset(point: LineTrendPoint): Offset {
        val x = xAt(point.index)
        val v = point.value.coerceIn(minV, maxV)
        val yT = ((v - minV) / (maxV - minV)).coerceIn(0f, 1f)
        val y = (plotTop + plotH) - plotH * yT
        return Offset(x, y)
    }

    points.zipWithNext().forEach { (start, end) ->
        val segmentColor = end.color ?: start.color ?: color
        drawLine(
            color = segmentColor.copy(alpha = 0.95f),
            start = pointOffset(start),
            end = pointOffset(end),
            strokeWidth = 2.dp.toPx(),
            cap = StrokeCap.Round,
            pathEffect = pathEffect
        )
    }

    if (showPoints) points.forEach { point ->
        val markerColor = point.color ?: color
        val center = pointOffset(point)
        if (pointFill != null) drawCircle(
            color = pointFill,
            radius = 5.dp.toPx(),
            center = center
        )
        drawCircle(
            color = markerColor,
            radius = 5.dp.toPx(),
            center = center,
            style = Stroke(width = 2.dp.toPx())
        )
    }
}
