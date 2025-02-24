package majestic.graph.tools

import androidx.compose.ui.unit.Density
import majestic.graph.Axis
import majestic.graph.SpaceAxis
import majestic.graph.StepAxis

internal fun Axis.points(min: Float, max: Float, density: Density, inverted: Boolean) = when (this) {
    is SpaceAxis -> project(min, max, with(density) { spacing.toPx() }, inverted)
    is StepAxis -> project(min, max, inverted)
}

private fun SpaceAxis.project(min: Float, max: Float, dPos: Float, inverted: Boolean): List<Projected> {
    val count = (max - min) / dPos
    return project(min, max, dPos = dPos, count = count.toInt(), inverted)
}

private fun StepAxis.project(min: Float, max: Float, inverted: Boolean): List<Projected> {
    val dPos = (max - min) / (count.toFloat() - 1)
    return project(min, max, dPos, count - 1, inverted)
}

private fun Axis.project(min: Float, max: Float, dPos: Float, count: Int, inverted: Boolean): List<Projected> {
    val dPoint = (this.max - this.min) / count

    var point = this.min
    var pos = if (inverted) max else min
    val multiplier = if (inverted) -1 else 1
    return buildList {
        while (point < (this@project.max + dPoint) || if (inverted) (pos > min) else (pos < max)) {
            add(Projected(point, pos))
            point += dPoint
            pos += dPos * multiplier
        }
    }
}

internal data class Projected(val src: Float, val dst: Float)

internal data class YProjection(val src: Float, val dst: Float, val offset: Float)