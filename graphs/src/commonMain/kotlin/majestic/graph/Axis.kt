package majestic.graph

import androidx.compose.ui.unit.Dp

internal sealed interface Axis {
    val min: Float
    val max: Float
    val line: Line?
    val markers: Markers?
    val grid: Line?
}

internal data class StepAxis(
    override val min: Float,
    override val max: Float,
    override val line: Line?,
    override val markers: Markers?,
    override val grid: Line?,
    val count: Int
) : Axis

internal data class SpaceAxis(
    override val min: Float,
    override val max: Float,
    override val line: Line?,
    override val markers: Markers?,
    override val grid: Line?,
    val spacing: Dp
) : Axis