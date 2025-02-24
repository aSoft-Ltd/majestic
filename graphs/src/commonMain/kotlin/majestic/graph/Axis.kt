package majestic.graph

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp

internal sealed interface Axis {
    val min: Float
    val max: Float
    val line: Line?
    val ticks: Ticks?
    val grid: Line?
    val label: @Composable (value: Float) -> Unit
}

internal data class StepAxis(
    override val min: Float,
    override val max: Float,
    override val line: Line?,
    override val ticks: Ticks?,
    override val grid: Line?,
    override val label: @Composable (value: Float) -> Unit,
    val count: Int
) : Axis

internal data class SpaceAxis(
    override val min: Float,
    override val max: Float,
    override val line: Line?,
    override val ticks: Ticks?,
    override val grid: Line?,
    override val label: @Composable (value: Float) -> Unit,
    val spacing: Dp
) : Axis