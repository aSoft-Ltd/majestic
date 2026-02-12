package majestic.payments.wallet.details.analytics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.graph.TransactionGroup
import majestic.graph.tools.Series
import majestic.layouts.Flex
import majestic.layouts.FlexCol
import majestic.layouts.FlexRow
import majestic.payments.wallet.details.analytics.tools.AnalyticColors
import majestic.payments.wallet.details.analytics.tools.TransactionFilter
import majestic.payments.wallet.details.analytics.tools.TransactionFilters

private fun Modifier.card(background: Color, orientation: ScreenOrientation) = this
    .clip(RoundedCornerShape(if (orientation is Landscape) 10.dp else 5.dp))
    .background(background)

@Composable
fun Analytics(
    colors: AnalyticColors,
    series: List<Series>,
    groups: List<TransactionGroup>,
    orientation: ScreenOrientation,
    modifier: Modifier = Modifier
) = Column(modifier = modifier) {
    FilterHeader(
        colors = colors.header,
        orientation = orientation,
        modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp)
    )
    Flex(
        modifier = Modifier.fillMaxWidth(),
        orientation = orientation,
        col = FlexCol(arrangement = Arrangement.spacedBy(10.dp)),
        row = FlexRow(arrangement = Arrangement.spacedBy(10.dp))
    ) {
        if (orientation is Landscape) AnalyticCard(
            title = "Transactions",
            colors = colors.header,
            modifier = Modifier.card(colors.background, orientation).weight(1f)
        ) {
            AnalyticGraph(
                colors = colors.graph,
                series = emptyList(),
                groups = emptyList(),
                orientation = orientation,
                modifier = Modifier.fillMaxSize()
            )
        }

        var transactionFilter by remember { mutableStateOf(TransactionFilter.SPLINT) }
        AnalyticCard(
            title = "Transactions",
            colors = colors.header,
            filters = {
                TransactionFilters(
                    filter = transactionFilter,
                    color = colors.foreground,
                    orientation = orientation,
                    onChange = { transactionFilter = it },
                )
            },
            modifier = Modifier.card(colors.background, orientation)
                .then(
                    when (orientation) {
                        is Portrait -> Modifier.fillMaxWidth().heightIn(min = 200.dp, max = 400.dp)
                        is Landscape -> Modifier.weight(1f)
                    }
                )
        ) {
            AnalyticGraph(
                colors = colors.graph,
                series = series,
                groups = groups,
                orientation = orientation,
                modifier = Modifier.fillMaxWidth()
                    .padding(if (orientation is Landscape) 10.dp else 5.dp)
            )
        }
    }
}
