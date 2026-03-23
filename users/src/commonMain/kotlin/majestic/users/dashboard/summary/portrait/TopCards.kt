package majestic.users.dashboard.summary.portrait

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import composex.screen.orientation.ScreenOrientation
import majestic.shared.dashboards.SummaryCard
import majestic.shared.tools.rememberHoverBackground
import majestic.users.dashboard.summary.SummaryCardListProps
import majestic.users.dashboard.summary.tools.getGraphColor
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun TopCards(
    modifier: Modifier,
    props: SummaryCardListProps,
    orientation: ScreenOrientation
) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.spacedBy(10.dp)
) {
    props.summaryList.take(2).forEach { item ->
        val (background, interactionSource) = rememberHoverBackground(
            background = props.summaryCardProps.colors.background,
            foreground = props.summaryCardProps.colors.foreground
        )

        SummaryCard(
            label = item.label,
            value = item.value,
            percentage = item.percentage,
            summaryStatus = item.summaryStatus,
            icon = painterResource(item.icon),
            props = props.summaryCardProps,
            orientation = orientation,
            modifier = summaryCardPortrait(background, orientation, interactionSource),
            graphColor = getGraphColor(item = item, props = props)
        )
    }
}