package majestic.users.dashboard.summary.portrait

import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.ScreenOrientation
import majestic.shared.dashboards.SummaryCard
import majestic.users.dashboard.summary.SummaryCardListProps
import majestic.users.dashboard.summary.tools.getGraphColor
import majestic.users.dashboard.summary.tools.getSummaryCardBg
import org.jetbrains.compose.resources.painterResource

internal fun RowScope.summaryCardPortrait(
    background: Color,
    orientation: ScreenOrientation,
    interactionSource: MutableInteractionSource
) = Modifier
    .weight(1f)
    .height(110.dp)
    .clip(RoundedCornerShape(if (orientation is Landscape) 20.dp else 10.dp))
    .background(background)
    .padding(if (orientation is Landscape) 20.dp else 10.dp)
    .pointerHoverIcon(PointerIcon.Hand)
    .hoverable(interactionSource = interactionSource)

@Composable
internal fun BottomCards(
    modifier: Modifier,
    props: SummaryCardListProps,
    orientation: ScreenOrientation
) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.spacedBy(10.dp)
) {
    props.summaryList.takeLast(3).forEach { item ->
        val interactionSource = remember { MutableInteractionSource() }
        val isHovered by interactionSource.collectIsHoveredAsState()
        val background = getSummaryCardBg(isHovered = isHovered, props = props)
        SummaryCard(
            label = item.label,
            value = item.value,
            percentage = item.percentage,
            summaryStatus = item.summaryStatus,
            icon = painterResource(item.icon),
            props = props.summaryCardProps,
            orientation = orientation,
            modifier = summaryCardPortrait(background = background, orientation, interactionSource),
            graphColor = getGraphColor(item = item, props = props)
        )
    }
}