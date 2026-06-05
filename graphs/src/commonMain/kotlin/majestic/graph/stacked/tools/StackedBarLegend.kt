package majestic.graph.stacked.tools

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.graph.stacked.StackedBarChartConfig
import majestic.graph.stacked.utils.StackedLegendItem

@Composable
fun StackedBarLegend(
    config: StackedBarChartConfig,
    modifier: Modifier = Modifier
) {
    val style = TextStyle(color = config.colors.axisLabel.copy(alpha = 0.8f), fontSize = 12.sp)
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically
    ) {
        config.stackLabels.forEachIndexed { index, label ->
            val color = config.colors.stacks.getOrElse(index) { config.colors.stacks.last() }
            StackedLegendItem(label = label, color = color, style = style)
        }
    }
}
