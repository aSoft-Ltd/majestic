package majestic.graph.bazier.tools

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.PathMeasure
import androidx.compose.ui.text.TextMeasurer
import majestic.graph.bazier.LineChartConfig
import majestic.graph.bazier.utils.ChartLayoutInfo

@Composable
 internal fun ChartCanvas(
    config: LineChartConfig,
    layout: ChartLayoutInfo,
    textMeasurer: TextMeasurer,
    ySteps: Int,
    yMin: Float,
    yRange: Float,
    xDivisor: Int,
    animationProgress: Float,
    modifier: Modifier = Modifier
) {
    val pathMeasure = remember { PathMeasure() }
    val yTickLength = 8f
    val xTickLength = 8f
    val yLabelGap = 10f
    val xLabelGap = 10f

    Canvas(modifier = modifier.background(config.colors.background)) {
        if (config.grid.showHorizontalLines || config.grid.showVerticalLines) {
            drawChartGrid(config, layout, ySteps, xDivisor)
        }

        drawChartLabels(
            config = config,
            layout = layout,
            textMeasurer = textMeasurer,
            ySteps = ySteps,
            yMin = yMin,
            yRange = yRange,
            xDivisor = xDivisor,
            yTickLength = yTickLength,
            xTickLength = xTickLength,
            yLabelGap = yLabelGap,
            xLabelGap = xLabelGap
        )
        drawChartAxes(config, layout, xDivisor, xTickLength)

        drawChartData(
            config,
            layout,
            pathMeasure,
            animationProgress,
            yMin,
            yRange
        )
    }
}
