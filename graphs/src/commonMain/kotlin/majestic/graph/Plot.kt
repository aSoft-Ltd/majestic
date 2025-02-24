package majestic.graph

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import kollections.filter
import kollections.firstOrNull
import kotlin.time.Duration.Companion.seconds

sealed interface Plot

data class LinePlot(
    val color: Color,
    val stroke: Stroke,
    val type: Type,
    val markers: Markers?,
    val points: List<Point>
) : Plot {
    internal val cache by lazy { mutableMapOf<Point, Offset>() }

    enum class Type {
        Straight, Curve
    }

    internal fun find(offset: Offset, radius: Float): Mark? = cache.filter { (_, pos) ->
        val dx = pos.x - offset.x
        val dy = pos.y - offset.y
        dx * dx + dy * dy < radius * radius
    }.map {
        Mark(this, markers?.timeout ?: 3.seconds, it.key, offset)
    }.firstOrNull()
}