package majestic.users.dashboard.summary.portrait

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import composex.screen.orientation.ScreenOrientation
import majestic.users.dashboard.summary.SummaryCardListProps

@Composable
internal fun PortraitCards(
    modifier: Modifier,
    props: SummaryCardListProps,
    orientation: ScreenOrientation
) = Column(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(10.dp)
) {
    TopCards(
        modifier = Modifier.fillMaxWidth(),
        props = props,
        orientation = orientation
    )
    BottomCards(
        modifier = Modifier.fillMaxWidth(),
        props = props,
        orientation = orientation
    )
}

