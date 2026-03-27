package majestic.users.dashboard.tools.portraitHeader

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import majestic.button.appearence.cardButton
import majestic.button.appearence.plusButton
import majestic.button.basic.CardButton
import majestic.button.basic.PlusButton
import majestic.shared.users.dashboard.HeaderColors
import majestic.shared.users.label.dashboard.DashboardLabels
import majestic.users.dashboard.tools.ActiveView
import majestic.users.dashboard.tools.View

@Composable
internal fun PortraitHeader(
    modifier: Modifier,
    colors: HeaderColors,
    labels: DashboardLabels,
    navigator: ActiveView,
    add: (view: View) -> Unit,
    manage: (view: View) -> Unit
) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
) {
    TabsBox(
        labels = labels.tabs,
        colors = colors.tabItemColors,
        navigator = navigator,
        modifier = Modifier
    )
    Row(
        modifier = Modifier,
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CardButton(
            text = labels.roles.manage,
            modifier = Modifier.cardButton(
                color = colors.manage,
                onClick = { manage(navigator.view) }
            )
        )

        PlusButton(
            modifier = Modifier.plusButton(
                colors = colors.add,
                onClick = { add(navigator.view) }
            ),
        )
    }
}