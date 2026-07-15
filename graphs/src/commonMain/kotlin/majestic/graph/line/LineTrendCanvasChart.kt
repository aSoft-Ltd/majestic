package majestic.graph.line

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun LineTrendCanvasChart(
    colors: LineTrendChartColors,
    isLandscape: Boolean,
    series: List<LineTrendSeries>,
    minV: Float = 0f,
    maxV: Float = 12f,
    yTitle: String? = "# of",
    xTitle: String? = "Months",
    xLabels: List<String>? = null,
    yTicks: List<Float>? = null,
    xInsetFraction: Float = 0f,
    modifier: Modifier = Modifier
) {
    val months = remember { listOf("JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL") }
    val labels = xLabels ?: months

    LineTrendCanvas(
        colors = colors,
        isLandscape = isLandscape,
        series = series,
        xLabels = labels,
        minV = minV,
        maxV = maxV,
        yTitle = yTitle,
        xTitle = xTitle,
        yTicks = yTicks,
        xInsetFraction = xInsetFraction,
        modifier = modifier
    )
}
