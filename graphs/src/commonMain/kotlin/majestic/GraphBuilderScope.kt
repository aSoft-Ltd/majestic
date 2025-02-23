package majestic

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

class GraphBuilderScope {
    internal var y: Axis? = null
    internal var x: Axis? = null
    fun y(
        min: Float = 0f,
        max: Float = 100f,
        line: Line? = Line.axis,
        markers: Markers? = Markers(),
        grid: Line? = Line.grid,
        step: Float
    ) {
        require(step > 0) { "Step must be greater than 0" }
        val count = (max - min) / step
        y = StepAxis(min, max, line, markers, grid, count.toInt())
    }

    fun y(
        min: Float = 0f,
        max: Float = 100f,
        line: Line? = Line.axis,
        markers: Markers? = Markers(),
        grid: Line? = Line.axis,
        spacing: Dp
    ) {
        y = SpaceAxis(min, max, line, markers, grid, spacing)
    }

    fun x(
        min: Float = 0f,
        max: Float = 100f,
        line: Line? = Line.axis,
        markers: Markers? = Markers(),
        grid: Line? = Line.grid,
        step: Float
    ) {
        require(step > 0) { "Step must be greater than 0" }
        val count = (max - min) / step
        x = StepAxis(min, max, line, markers, grid, count.toInt())
    }

    fun x(
        min: Float = 0f,
        max: Float = 100f,
        line: Line? = Line.axis,
        markers: Markers? = Markers(),
        grid: Line? = Line.axis,
        spacing: Dp
    ) {
        x = SpaceAxis(min, max, line, markers, grid, spacing)
    }

    internal val plots = mutableListOf<Plot>()
    fun line(
        color: Color = Color.Black,
        stroke: Stroke = Stroke(),
        type: LinePlot.Type = LinePlot.Type.Straight,
        points: List<Point>
    ) {
        plots += LinePlot(color, stroke, type, points)
    }
}