package majestic.users.dashboard.summary

import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.ScreenOrientation
import majestic.tooling.onClick
import org.jetbrains.compose.resources.painterResource


data class SummaryCardListProps(
    val summaryCardProps: SummaryCardColorProps,
    val summaryList: List<Summary>,
    val orientation: ScreenOrientation
)

@Composable
internal fun SummaryCardList(
    props: SummaryCardListProps,
    modifier: Modifier = Modifier,
) {
    val orientation = props.orientation

    if (orientation == Landscape) Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(20.dp),
    ) {

        props.summaryList.forEach {
            val interactionSource = remember { MutableInteractionSource() }
            val isHovered by interactionSource.collectIsHoveredAsState()
            val background = when (isHovered) {
                true -> props.summaryCardProps.colors.foreground.copy(0.02f)
                    .compositeOver(props.summaryCardProps.colors.background)

                false -> props.summaryCardProps.colors.background
            }
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
                label = it.label,
                value = it.value,
                percentage = it.percentage,
                userDetailsStatus = it.userDetailsStatus,
                summaryStatus = it.summaryStatus,
                icon = painterResource(it.icon),
                props = props.summaryCardProps,
                orientation = orientation
            )
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                props.summaryList.take(2).forEach { item ->
                    val interactionSource = remember { MutableInteractionSource() }
                    val isHovered by interactionSource.collectIsHoveredAsState()
                    val background = when (isHovered) {
                        true -> props.summaryCardProps.colors.foreground.copy(0.02f)
                            .compositeOver(props.summaryCardProps.colors.background)

                        false -> props.summaryCardProps.colors.background
                    }

                    SummaryCard(
                        label = item.label,
                        value = item.value,
                        percentage = item.percentage,
                        userDetailsStatus = item.userDetailsStatus,
                        summaryStatus = item.summaryStatus,
                        icon = painterResource(item.icon),
                        props = props.summaryCardProps,
                        orientation = orientation,
                        modifier = Modifier
                            .weight(1f) // Equal width for both cards
                            .height(110.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .background(background)
                            .padding(if (orientation is Landscape) 20.dp else 10.dp)
                            .pointerHoverIcon(PointerIcon.Hand)
                            .hoverable(interactionSource = interactionSource)
                            .onClick { },
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                props.summaryList.takeLast(3).forEach { item ->

                    val interactionSource = remember { MutableInteractionSource() }
                    val isHovered by interactionSource.collectIsHoveredAsState()
                    val background = when (isHovered) {
                        true -> props.summaryCardProps.colors.foreground.copy(0.02f)
                            .compositeOver(props.summaryCardProps.colors.background)

                        false -> props.summaryCardProps.colors.background
                    }
                    SummaryCard(
                        label = item.label,
                        value = item.value,
                        percentage = item.percentage,
                        userDetailsStatus = item.userDetailsStatus,
                        summaryStatus = item.summaryStatus,
                        icon = painterResource(item.icon),
                        props = props.summaryCardProps,
                        orientation = orientation,
                        modifier = Modifier
                            .weight(1f) // Equal width for all three cards
                            .height(110.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .background(background)
                            .padding(if (orientation is Landscape) 20.dp else 10.dp)
                            .pointerHoverIcon(PointerIcon.Hand)
                            .hoverable(interactionSource = interactionSource)
                            .onClick { },
                    )
                }
            }
        }
    }
}