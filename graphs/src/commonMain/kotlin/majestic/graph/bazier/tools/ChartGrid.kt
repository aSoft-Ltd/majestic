package majestic.graph.bazier.tools

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawScope
import majestic.graph.bazier.LineChartConfig
import majestic.graph.bazier.utils.ChartLayoutInfo

 internal fun DrawScope.drawChartGrid(
    config: LineChartConfig,
    layout: ChartLayoutInfo,
    ySteps: Int,
    xDivisor: Int
) {
    if (config.grid.showHorizontalLines) {
        repeat(ySteps + 1) { i ->
            val fraction = i.toFloat() / ySteps
            val y = layout.chartBottom - (fraction * layout.chartHeight)
            if (i > 0) {
                drawLine(
                    color = config.colors.grid,
                    start = Offset(layout.chartLeft, y),
                    end = Offset(layout.chartRight, y),
                    strokeWidth = 1f
                )
            }
        }
    }

    if (config.grid.showVerticalLines) {
        repeat(xDivisor + 1) { i ->
            val x = layout.chartLeft + (i.toFloat() / xDivisor) * layout.chartWidth
            if (i > 0) {
                drawLine(
                    color = config.colors.grid,
                    start = Offset(x, layout.chartTop),
                    end = Offset(x, layout.chartBottom),
                    strokeWidth = 1f
                )
            }
        }
    }
}
