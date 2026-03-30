package majestic.graph.bazier.utils

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path

 fun buildBezierPath(points: List<Offset>): Path {
    val path = Path()
    if (points.isEmpty()) return path
    path.moveTo(points[0].x, points[0].y)
    if (points.size == 1) return path

    for (i in 0 until points.size - 1) {
        val p0 = points[i]
        val p1 = points[i + 1]
        val controlX = (p0.x + p1.x) / 2f
        path.cubicTo(
            x1 = controlX, y1 = p0.y,
            x2 = controlX, y2 = p1.y,
            x3 = p1.x, y3 = p1.y
        )
    }
    return path
}