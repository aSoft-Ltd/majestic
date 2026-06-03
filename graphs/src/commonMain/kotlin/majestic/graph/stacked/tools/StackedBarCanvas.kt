package majestic.graph.stacked.tools

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextMeasurer
import majestic.graph.stacked.StackedBarChartConfig
import majestic.graph.stacked.utils.StackedChartLayoutInfo

@Composable
internal fun StackedBarCanvas(
    config: StackedBarChartConfig,
    layout: StackedChartLayoutInfo,
    textMeasurer: TextMeasurer,
    animationProgress: Float,
    modifier: Modifier = Modifier
) {
    val yRange = ((config.yMax ?: 200f) - config.yMin).coerceAtLeast(1f)
    val ySteps = config.ySteps
    val yLabelGap = 10f

    Canvas(modifier = modifier.background(config.colors.background)) {
        if (config.grid.showHorizontalLines || config.grid.showVerticalLines) {
            drawStackedGrid(config, layout, ySteps)
        }

        drawStackedLabels(
            config = config,
            layout = layout,
            textMeasurer = textMeasurer,
            ySteps = ySteps,
            yMin = config.yMin,
            yRange = yRange,
            yLabelGap = yLabelGap
        )

        drawStackedAxes(config, layout)

        drawStackedBars(
            config = config,
            layout = layout,
            animationProgress = animationProgress,
            yRange = yRange
        )
    }
}
