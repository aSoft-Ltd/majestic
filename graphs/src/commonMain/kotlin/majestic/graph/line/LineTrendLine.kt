package majestic.graph.line

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
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
    color: Color
) {
    if (points.size < 2) return
    val path = Path()
    points.forEachIndexed { i, p ->
        val x = xAt(p.index)
        val v = p.value.coerceIn(minV, maxV)
        val yT = ((v - minV) / (maxV - minV)).coerceIn(0f, 1f)
        val y = (plotTop + plotH) - plotH * yT
        if (i == 0) path.moveTo(x, y) else path.lineTo(x, y)
    }

    drawPath(
        path = path,
        color = color.copy(alpha = 0.95f),
        style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round)
    )
}
