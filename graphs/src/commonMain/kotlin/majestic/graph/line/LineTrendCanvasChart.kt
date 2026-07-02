package majestic.graph.line

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun LineTrendCanvasChart(
    colors: LineTrendChartColors,
    isLandscape: Boolean,
    series: List<LineTrendSeries>,
    yTitle: String? = "# of",
    xTitle: String? = "Months",
    modifier: Modifier = Modifier
) {
    val months = remember { listOf("JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL") }

    LineTrendCanvas(
        colors = colors,
        isLandscape = isLandscape,
        series = series,
        xLabels = months,
        minV = 0f,
        maxV = 12f,
        yTitle = yTitle,
        xTitle = xTitle,
        modifier = modifier
    )
}
