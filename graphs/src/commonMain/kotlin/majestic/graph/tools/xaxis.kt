package majestic.graph.tools

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawScope
import majestic.graph.Axis

internal fun DrawScope.drawXAxis(axis: Axis, points: List<Projected>) {
    val ticks = axis.ticks
    if (ticks != null) for (point in points) {
        val intercept = point.dst
        drawLine(color = ticks.color, start = Offset(intercept, size.height), end = Offset(intercept, size.height + ticks.length.toPx()), strokeWidth = ticks.stroke.width, pathEffect = ticks.stroke.pathEffect)
    }

    val line = axis.line
    if (line != null) {
        drawLine(color = line.color, start = Offset(0f, size.height), end = Offset(size.width, size.height), pathEffect = line.stroke.pathEffect)
    }

    val grid = axis.grid
    if (grid != null) for (point in points) {
        val intercept = point.dst
        drawLine(color = grid.color, start = Offset(intercept, 0f), end = Offset(intercept, size.height), strokeWidth = grid.stroke.width, cap = grid.stroke.cap, pathEffect = grid.stroke.pathEffect)
    }
}