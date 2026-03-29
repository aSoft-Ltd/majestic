package majestic.graph.bazier.tools

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import majestic.graph.bazier.LineChartConfig
import majestic.graph.bazier.utils.LegendItem

@Composable
internal fun ChartLegend(
    config: LineChartConfig,
    modifier: Modifier = Modifier
) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterHorizontally),
    verticalAlignment = Alignment.CenterVertically
) {
    config.series.forEach { series ->
        LegendItem(label = series.label, colors = series.color)
    }
}
