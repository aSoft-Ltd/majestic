package majestic.graph.stacked.tools

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.DrawScope
import majestic.graph.stacked.StackedBarChartConfig
import majestic.graph.stacked.utils.StackedChartLayoutInfo

internal fun DrawScope.drawStackedGrid(
    config: StackedBarChartConfig,
    layout: StackedChartLayoutInfo,
    ySteps: Int
) {
    val dash = if (config.grid.isDashed) PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f) else null
    if (config.grid.showHorizontalLines) {
        repeat(ySteps + 1) { i ->
            val fraction = i.toFloat() / ySteps
            val y = layout.chartBottom - (fraction * layout.chartHeight)
            if (i > 0) {
                drawLine(
                    color = config.colors.grid,
                    start = Offset(layout.chartLeft, y),
                    end = Offset(layout.chartRight, y),
                    strokeWidth = 1f,
                    pathEffect = dash
                )
            }
        }
    }

    if (config.grid.showVerticalLines) {
        val dataSize = config.data.size
        if (dataSize > 0) {
            val slotWidth = layout.chartWidth / dataSize
            for (i in 0..dataSize) {
                val x = layout.chartLeft + i * slotWidth
                drawLine(
                    color = config.colors.grid,
                    start = Offset(x, layout.chartTop),
                    end = Offset(x, layout.chartBottom),
                    strokeWidth = 1f,
                    pathEffect = dash
                )
            }
        }
    }
}
