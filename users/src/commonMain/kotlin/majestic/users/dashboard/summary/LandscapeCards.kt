package majestic.users.dashboard.summary

import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.ScreenOrientation
import majestic.shared.dashboards.SummaryCard
import majestic.shared.tools.rememberHoverBackground
import majestic.tooling.onClick
import majestic.users.dashboard.summary.tools.getGraphColor
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun LandscapeCards(
    modifier: Modifier,
    props: SummaryCardListProps,
    orientation: ScreenOrientation
) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.spacedBy(20.dp),
) {

    props.summaryList.forEach { item ->
        val (background, interactionSource) = rememberHoverBackground(
            background = props.summaryCardProps.colors.background,
            foreground = props.summaryCardProps.colors.foreground
        )

        SummaryCard(
            modifier = Modifier
                .width(300.dp)
                .height(170.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(background)
                .padding(if (orientation is Landscape) 20.dp else 10.dp)
                .pointerHoverIcon(PointerIcon.Hand)
                .hoverable(interactionSource = interactionSource)
                .onClick { },
            label = item.label,
            value = item.value,
            percentage = item.percentage,
            summaryStatus = item.summaryStatus,
            icon = painterResource(item.icon),
            props = props.summaryCardProps,
            orientation = orientation,
            graphColor = getGraphColor(item = item, props = props)
        )
    }
}