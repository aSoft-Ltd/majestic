package majestic.users.dashboard.summary

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.shared.dashboards.Summary
import majestic.shared.dashboards.SummaryCardColorProps
import majestic.users.dashboard.summary.portrait.PortraitCards
import majestic.users.dashboard.tools.UserDetailsStatus


data class SummaryCardListProps(
    val summaryCardProps: SummaryCardColorProps,
    val summaryList: List<Summary<UserDetailsStatus>>,
    val orientation: ScreenOrientation
)

@Composable
internal fun SummaryCardList(
    props: SummaryCardListProps,
    modifier: Modifier = Modifier,
) = when (val orientation = props.orientation) {
    is Landscape -> LandscapeCards(
        modifier = modifier,
        props = props,
        orientation = orientation,
    )

    is Portrait -> PortraitCards(
        modifier = modifier,
        props = props,
        orientation = orientation
    )
}