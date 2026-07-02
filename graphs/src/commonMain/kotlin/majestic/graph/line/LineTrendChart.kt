package majestic.graph.line

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
    yTitle: String? = "# of",
    xTitle: String? = "Months",
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
        yTitle = yTitle,
        xTitle = xTitle,
        modifier = Modifier.fillMaxSize()
    )
}