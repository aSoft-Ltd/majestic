package majestic

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import majestic.graph.Axis
import majestic.graph.Line
import majestic.graph.LinePlot
import majestic.graph.Markers
import majestic.graph.Plot
import majestic.graph.Point
import majestic.graph.SpaceAxis
import majestic.graph.StepAxis
import majestic.graph.Ticks

class GraphBuilderScope {
    internal var y: Axis? = null
    internal var x: Axis? = null
    fun y(
        min: Float = 0f,
        max: Float = 100f,
        step: Float,
        line: Line? = null,
        grid: Line? = null,
        ticks: Ticks? = null,
        label: @Composable (Float) -> Unit = {
            Text("$it", color = ticks?.color ?: grid?.color ?: line?.color ?: Color.White)
        }
    ) {
        require(step > 0) { "Step must be greater than 0" }
        val count = (max - min) / step
        y = StepAxis(min, max, line, ticks, grid, label, count.toInt() + 1)
    }

    fun y(
        min: Float = 0f,
        max: Float = 100f,
        spacing: Dp,
        line: Line? = null,
        grid: Line? = null,
        ticks: Ticks? = null,
        label: @Composable (Float) -> Unit = {
            Text("$it", color = ticks?.color ?: grid?.color ?: line?.color ?: Color.White)
        }
    ) {
        y = SpaceAxis(min, max, line, ticks, grid, label, spacing)
    }

    fun x(
        min: Float = 0f,
        max: Float = 100f,
        step: Float,
        line: Line? = null,
        grid: Line? = null,
        ticks: Ticks? = null,
        label: @Composable (Float) -> Unit = {
            Text("$it", color = ticks?.color ?: grid?.color ?: line?.color ?: Color.White)
        }
    ) {
        require(step > 0) { "Step must be greater than 0" }
        val count = (max - min) / step
        x = StepAxis(min, max, line, ticks, grid, label, count.toInt() + 1)
    }

    fun x(
        min: Float = 0f,
        max: Float = 100f,
        spacing: Dp,
        line: Line? = null,
        grid: Line? = null,
        ticks: Ticks? = null,
        label: @Composable (Float) -> Unit = {
            Text("$it", color = ticks?.color ?: grid?.color ?: line?.color ?: Color.White)
        }
    ) {
        x = SpaceAxis(min, max, line, ticks, grid, label, spacing)
    }

    internal val plots = mutableListOf<Plot>()
    fun line(
        color: Color = Color.Black,
        stroke: Stroke = Stroke(),
        type: LinePlot.Type = LinePlot.Type.Straight,
        markers: Markers? = null,
        points: List<Point>
    ) {
        plots += LinePlot(color, stroke, type, markers, points)
    }
}