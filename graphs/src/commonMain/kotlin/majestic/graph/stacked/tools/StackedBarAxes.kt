package majestic.graph.stacked.tools

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawScope
import majestic.graph.stacked.StackedBarChartConfig
import majestic.graph.stacked.utils.StackedChartLayoutInfo

internal fun DrawScope.drawStackedAxes(
    config: StackedBarChartConfig,
    layout: StackedChartLayoutInfo
) {
    val axisColor = config.colors.axisLabel.copy(alpha = 0.3f)
    val strokeWidth = 1f

    // X Axis
    if (config.grid.showXAxisLine) {
        drawLine(
            color = axisColor,
            start = Offset(layout.chartLeft, layout.chartBottom),
            end = Offset(layout.chartRight, layout.chartBottom),
            strokeWidth = strokeWidth
        )
    }

    // Y Axis
    if (config.grid.showYAxisLine) {
        drawLine(
            color = axisColor,
            start = Offset(layout.chartLeft, layout.chartTop),
            end = Offset(layout.chartLeft, layout.chartBottom),
            strokeWidth = strokeWidth
        )
    }

    // Dots (Tick marks)
    if (config.grid.showDots) {
        // Y Axis Dots
        val ySteps = config.ySteps
        repeat(ySteps + 1) { i ->
            val fraction = i.toFloat() / ySteps
            val y = layout.chartBottom - (fraction * layout.chartHeight)
            drawCircle(
                color = axisColor,
                radius = 2.5f,
                center = Offset(layout.chartLeft, y)
            )
        }

        // X Axis Dots
        val dataSize = config.data.size
        if (dataSize > 0) {
            val slotWidth = layout.chartWidth / dataSize
            repeat(dataSize + 1) { i ->
                val x = layout.chartLeft + (i * slotWidth)
                drawCircle(
                    color = axisColor,
                    radius = 2.5f,
                    center = Offset(x, layout.chartBottom)
                )
            }
        }
    }
}
