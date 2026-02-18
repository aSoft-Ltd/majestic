package majestic.users.dashboard.roles.tools

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import composex.screen.orientation.ScreenOrientation
import majestic.shared.users.dashboard.CardStatColors

@Composable
fun Footer(
    stats: List<Stat>,
    colors: CardStatColors,
    orientation: ScreenOrientation,
    modifier: Modifier = Modifier
) = Row(
    horizontalArrangement = Arrangement.spacedBy(24.dp),
    verticalAlignment = Alignment.CenterVertically,
    modifier = modifier,
) {
    stats.forEach { stat ->
        CardStat(
            modifier = Modifier.wrapContentSize(),
            colors = colors,
            stat = stat,
            orientation = orientation
        )
    }
}