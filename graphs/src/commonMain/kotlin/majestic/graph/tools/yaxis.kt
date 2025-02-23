package majestic.graph.tools

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawScope
import majestic.graph.Axis

internal fun DrawScope.drawYAxis(axis: Axis, points: List<Projected>) {
    val markers = axis.markers
    if (markers != null) for (point in points) {
        val intercept = point.dst
        drawLine(color = markers.color, start = Offset(0f, intercept), end = Offset(markers.length.toPx(), intercept), strokeWidth = markers.width.toPx())
    }

    val line = axis.line
    if (line != null) {
        drawLine(color = line.color, start = Offset(0f, 0f), end = Offset(0f, size.height))
    }

    val grid = axis.grid
    if (grid != null) for (point in points) {
        val intercept = point.dst
        drawLine(color = grid.color, start = Offset(0f, intercept), end = Offset(size.width, intercept), strokeWidth = grid.stroke.width, cap = grid.stroke.cap)
    }
}