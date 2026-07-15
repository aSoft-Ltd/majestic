package majestic.graph.line

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LineTrendChart(
    colors: LineTrendChartColors,
    isLandscape: Boolean,
    legend: @Composable () -> Unit,
    visibleSeriesNames: Set<String>,
    series: List<LineTrendSeries>,
    minV: Float = 0f,
    maxV: Float = 12f,
    yTitle: String? = "# of",
    xTitle: String? = "Months",
    xLabels: List<String>? = null,
    yTicks: List<Float>? = null,
    xInsetFraction: Float = 0f,
    modifier: Modifier = Modifier
) = Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(10.dp)) {
    val visibleSeries = remember(series, visibleSeriesNames) {
        if (visibleSeriesNames.isEmpty()) emptyList()
        else series.filter { it.name in visibleSeriesNames }
    }
    if (isLandscape) {
        legend()
    }
    LineTrendCanvasChart(
        colors = colors,
        isLandscape = isLandscape,
        series = visibleSeries,
        xLabels = xLabels,
        minV = minV,
        maxV = maxV,
        yTitle = yTitle,
        xTitle = xTitle,
        yTicks = yTicks,
        xInsetFraction = xInsetFraction,
        modifier = Modifier.fillMaxSize().padding(bottom = 10.dp)
    )
}