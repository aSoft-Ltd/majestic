package majestic.graph.bazier

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.graph.bazier.tools.ChartCanvas
import majestic.graph.bazier.tools.ChartLegend
import majestic.graph.bazier.utils.calculateChartLayoutInfo
import kotlin.math.ceil

@Composable
fun BezierLineChart(
    config: LineChartConfig,
    modifier: Modifier = Modifier,
    legend: @Composable (LineChartConfig) -> Unit = {
        ChartLegend(
            it,
            modifier = Modifier.fillMaxWidth()
        )
    },
    yAxisTitle: @Composable (LineChartConfig) -> Unit = {
        if (it.yAxisLabel.isNotEmpty()) {
            Text(
                text = it.yAxisLabel,
                color = it.colors.axisLabel,
                fontSize = 16.sp,
                modifier = Modifier
                    .rotate(-90f)
            )
        }
    }
) {
    val density = LocalDensity.current
    val textMeasurer = rememberTextMeasurer()
    val resolvedYMax = remember(config) {
        config.yMax ?: (config.series.flatMap { it.values }.maxOrNull()?.let {
            ceil(it.toDouble()).toFloat() + 1f
        } ?: 12f)
    }

    val animationProgress = remember { Animatable(0f) }
    LaunchedEffect(config.series) {
        animationProgress.snapTo(0f)
        animationProgress.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1500, easing = LinearOutSlowInEasing)
        )
    }

    Column(modifier = modifier) {
        legend(config)

        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
            val widthPx = with(density) { maxWidth.toPx() }
            val heightPx = with(density) { maxHeight.toPx() }

            val layout = calculateChartLayoutInfo(
                widthPx = widthPx,
                heightPx = heightPx,
                paddingLeft = with(density) { config.padding.left.toPx() },
                paddingRight = with(density) { config.padding.right.toPx() },
                paddingTop = with(density) { config.padding.top.toPx() },
                paddingBottom = with(density) { config.padding.bottom.toPx() }
            )
            val yRange = resolvedYMax - config.yMin
            val ySteps = config.ySteps.coerceAtLeast(1)
            val xCount = config.xLabels.size
            val xDivisor = (xCount - 1).coerceAtLeast(1)

            ChartCanvas(
                config = config,
                layout = layout,
                textMeasurer = textMeasurer,
                ySteps = ySteps,
                yMin = config.yMin,
                yRange = yRange,
                xDivisor = xDivisor,
                animationProgress = animationProgress.value,
                modifier = Modifier.fillMaxSize()
            )

            Box(modifier = Modifier.align(Alignment.CenterStart).offset(x = (-16).dp)) {
                yAxisTitle(config)
            }
        }
    }
}
