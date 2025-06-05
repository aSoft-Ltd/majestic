package majestic.graph.tools

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawScope
import majestic.graph.Axis

internal fun DrawScope.drawYAxis(axis: Axis, points: List<Projected>) {
    val ticks = axis.ticks
    if (ticks != null) for (point in points) {
        val intercept = point.dst
        drawLine(color = ticks.color, start = Offset(0f, intercept), end = Offset(ticks.length.toPx(), intercept), strokeWidth = ticks.stroke.width, pathEffect = ticks.stroke.pathEffect)
    }

    val line = axis.line
    if (line != null) {
        drawLine(color = line.color, start = Offset(0f, 0f), end = Offset(0f, size.height), pathEffect = line.stroke.pathEffect)
    }

    val grid = axis.grid
    if (grid != null) for (point in points) {
        val intercept = point.dst
        drawLine(color = grid.color, start = Offset(0f, intercept), end = Offset(size.width, intercept), strokeWidth = grid.stroke.width, cap = grid.stroke.cap, pathEffect = grid.stroke.pathEffect)
    }
}