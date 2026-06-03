package majestic.graph.stacked

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.graph.stacked.tools.StackedBarCanvas
import majestic.graph.stacked.tools.StackedBarLegend
import majestic.graph.stacked.utils.calculateStackedChartLayoutInfo

@Composable
fun StackedBarChart(
    config: StackedBarChartConfig,
    modifier: Modifier = Modifier,
    legend: @Composable (StackedBarChartConfig) -> Unit = {
        StackedBarLegend(
            it,
            modifier = Modifier.padding(bottom = 8.dp).fillMaxWidth()
        )
    }
) {
    val density = LocalDensity.current
    val textMeasurer = rememberTextMeasurer()

    val animationProgress = remember { Animatable(0f) }
    LaunchedEffect(config.data) {
        animationProgress.snapTo(0f)
        animationProgress.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1500, easing = LinearOutSlowInEasing)
        )
    }

    Column(modifier = modifier) {
        if (config.title.isNotEmpty()) {
            val titleStyle = TextStyle(
                color = config.colors.axisLabel.copy(alpha = 0.6f),
                fontSize = 11.sp,
                fontWeight = FontWeight.Normal
            )
            Text(
                text = config.title,
                style = titleStyle,
                modifier = Modifier.padding(start = 12.dp, top = 5.dp)
            )
        }

        BoxWithConstraints(modifier = Modifier.padding(top = 10.dp).fillMaxWidth().weight(1f)) {
            val widthPx = with(density) { maxWidth.toPx() }
            val heightPx = with(density) { maxHeight.toPx() }

            val layout = calculateStackedChartLayoutInfo(
                widthPx = widthPx,
                heightPx = heightPx,
                paddingLeft = with(density) { config.padding.left.toPx() },
                paddingRight = with(density) { config.padding.right.toPx() },
                paddingTop = with(density) { config.padding.top.toPx() },
                paddingBottom = with(density) { config.padding.bottom.toPx() }
            )

            StackedBarCanvas(
                config = config,
                layout = layout,
                textMeasurer = textMeasurer,
                animationProgress = animationProgress.value,
                modifier = Modifier.fillMaxSize()
            )
        }

        legend(config)
    }
}
