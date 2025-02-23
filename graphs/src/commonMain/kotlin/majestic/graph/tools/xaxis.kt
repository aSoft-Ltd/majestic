package majestic.graph.tools

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawScope
import majestic.graph.Axis

internal fun DrawScope.drawXAxis(axis: Axis, points: List<Projected>) {
    val markers = axis.markers
    if (markers != null) for (point in points) {
        val intercept = point.dst
        drawLine(color = markers.color, start = Offset(intercept, size.height), end = Offset(intercept, size.height + markers.length.toPx()), strokeWidth = markers.width.toPx())
    }

    val line = axis.line
    if (line != null) {
        drawLine(color = line.color, start = Offset(0f, size.height), end = Offset(size.width, size.height))
    }

    val grid = axis.grid
    if (grid != null) for (point in points) {
        val intercept = point.dst
        drawLine(color = grid.color, start = Offset(intercept, 0f), end = Offset(intercept, size.height), strokeWidth = grid.stroke.width, cap = grid.stroke.cap)
    }
}