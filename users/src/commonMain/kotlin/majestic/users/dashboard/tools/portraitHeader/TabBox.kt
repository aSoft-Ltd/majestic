package majestic.users.dashboard.tools.portraitHeader

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import majestic.shared.users.label.dashboard.DashboardTabLabels
import majestic.users.dashboard.tools.ActiveView

@Composable
internal fun TabsBox(
    modifier: Modifier,
    colors: TabItemColors,
    navigator: ActiveView,
    labels: DashboardTabLabels,
) = Box(modifier = modifier) {
    Tabs(
        pages = labels.getItems(),
        colors = colors,
        active = navigator,
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .horizontalScroll(rememberScrollState())
    )
}
