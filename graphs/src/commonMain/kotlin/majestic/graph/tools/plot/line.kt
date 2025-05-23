package majestic.graph.tools.plot

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import majestic.graph.LinePlot
import majestic.graph.Point
import majestic.graph.tools.Projected
import majestic.graph.tools.YProjection

internal fun DrawScope.plot(graph: LinePlot, x: Projected, y: YProjection) {
    val factor = Offset(x.dst / x.src, 0f)
    when (graph.type) {
        LinePlot.Type.Straight -> graph.points.windowed(2) { (a, b) ->
            drawLine(
                start = graph.cache.getOrPut(a) { a.project(factor, y) },
                end = graph.cache.getOrPut(b) { b.project(factor, y) },
                color = graph.color,
                strokeWidth = graph.stroke.width,
                cap = graph.stroke.cap,
                pathEffect = graph.stroke.pathEffect
            )
        }

        LinePlot.Type.Curve -> {
            val points = graph.points.map { graph.cache.getOrPut(it) { it.project(factor, y) } }
            if (points.size > 1) {
                val path = Path().apply {
                    moveTo(points.first().x, points.first().y)
                    for (i in 0 until points.size - 1) {
                        val p0 = points[maxOf(i - 1, 0)]
                        val p1 = points[i]
                        val p2 = points[i + 1]
                        val p3 = points[minOf(i + 2, points.size - 1)]

                        val cp1x = p1.x + (p2.x - p0.x) / 6
                        val cp1y = p1.y + (p2.y - p0.y) / 6

                        val cp2x = p2.x - (p3.x - p1.x) / 6
                        val cp2y = p2.y - (p3.y - p1.y) / 6
                        cubicTo(cp1x, cp1y, cp2x, cp2y, p2.x, p2.y)
                    }
                }
                drawPath(path, color = graph.color, style = graph.stroke)
            }
        }
    }

    val markers = graph.markers
    if (markers != null) for (point in graph.points) drawCircle(
        color = markers.color ?: graph.color,
        center = graph.cache.getOrPut(point) { point.project(factor, y) },
        radius = markers.radius.toPx(),
        style = markers.style
    )
}

// Projection
// yMax,offset (ideally zero)
// yMin,height (ideally size.height)
// slope = (offset - height)/(yMax - yMin)
// (y - offset) = slope * (ySrc - yMax)
private fun Point.project(factor: Offset, projection: YProjection) = Offset(
    x = x * factor.x,
    y = run {
        val axis = projection.axis
        val slope = projection.slope()
        projection.offset + slope * (y - axis.max)
    }
)

private fun YProjection.slope() = span / (axis.min - axis.max)