package majestic.payments.dashboard.wallet.chart

import androidx.compose.animation.core.EaseOutCubic
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import majestic.payments.dashboard.wallet.chart.tools.BarShape
import majestic.payments.dashboard.wallet.chart.tools.ChartOrientation

@Composable
fun BarChart(
    value: Float,
    color: Color,
    shape: BarShape,
    animationDuration: Int = 2000,
    orientation: ChartOrientation = ChartOrientation.Horizontal,
    modifier: Modifier = Modifier
) {
    var animationPlayed by remember { mutableStateOf(false) }
    val animationProgress by animateFloatAsState(
        targetValue = if (animationPlayed) 1f else 0f,
        animationSpec = tween(animationDuration, easing = EaseOutCubic),
        label = "bar_animation"
    )

    LaunchedEffect(Unit) {
        animationPlayed = true
    }

    Box(modifier = modifier) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val canvasWidth = size.width
            val canvasHeight = size.height
            if (canvasWidth <= 0 || canvasHeight <= 0) return@Canvas

            when (orientation) {
                ChartOrientation.Horizontal -> drawHorizontalBar(
                    value = value,
                    width = canvasWidth,
                    height = canvasHeight,
                    color = color,
                    animation = animationProgress,
                    shape = shape
                )

                ChartOrientation.Vertical -> drawVerticalBar(
                    value = value,
                    width = canvasWidth,
                    height = canvasHeight,
                    color = color,
                    animation = animationProgress,
                    shape = shape
                )
            }
        }
    }
}
