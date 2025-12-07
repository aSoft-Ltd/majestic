package majestic.payments.dashboard.transaction

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.ThemeColor
import majestic.graph.GraphCanvas
import majestic.graph.TransactionGroup
import majestic.graph.tools.GraphLegend
import majestic.graph.tools.Series

@Composable
fun TransactionGraph(
    theme: ThemeColor,
    axisColor: Color,
    textColor: Color,
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
            axisColor = axisColor,
            textColor = textColor,
            series = series,
            steps = 10,
            stepSymbol = "M",
            groups = visibleGroups,
            modifier = Modifier.fillMaxWidth().fillMaxHeight(.9f)
        )
        GraphLegend(
            theme = theme,
            series = series,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp)
        )
    }
}
