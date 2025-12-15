package majestic.users.dashboard.roles.tools

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import composex.screen.orientation.ScreenOrientation
import majestic.users.dashboard.roles.tools.Stat
import majestic.users.tools.dialogs.Flex

@Composable
fun Footer(
    stats: List<Stat>,
    colors: CardStatColors,
    orientation: ScreenOrientation,
    modifier: Modifier = Modifier
) = Flex(
    horizontalArrangement = Arrangement.spacedBy(24.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp),
    modifier = modifier,
    orientation = orientation,
    alignment = Alignment.CenterVertically,
) {
    stats.forEach { stat ->
        CardStat(
            modifier = Modifier.wrapContentSize(),
            colors = colors,
            stat = stat
        )
    }
}