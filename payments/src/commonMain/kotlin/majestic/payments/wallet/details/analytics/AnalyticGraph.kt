package majestic.payments.wallet.details.analytics

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.graph.GraphCanvas
import majestic.graph.TransactionGroup
import majestic.graph.tools.GraphLegend
import majestic.graph.tools.Series
import majestic.payments.tools.graph.GraphColors

@Composable
fun AnalyticGraph(
    colors: GraphColors,
    series: List<Series>,
    groups: List<TransactionGroup>,
    orientation: ScreenOrientation,
    modifier: Modifier = Modifier
) {
    val maxPackages = when (orientation) {
        is Portrait -> 5
        is Landscape -> 10
    }
    val visibleGroups = groups.take(maxPackages.coerceAtMost(groups.size))

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        GraphCanvas(
            axisColor = colors.axis,
            textColor = colors.text,
            series = series,
            steps = 10,
            stepSymbol = "M",
            groups = visibleGroups,
            modifier = Modifier.fillMaxWidth()
                .fillMaxHeight(.9f)
        )
        GraphLegend(
            color = colors.legend,
            series = series,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp)
        )
    }
}
