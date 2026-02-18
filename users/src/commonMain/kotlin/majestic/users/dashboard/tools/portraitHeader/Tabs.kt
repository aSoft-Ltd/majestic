package majestic.users.dashboard.tools.portraitHeader

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import majestic.shared.users.dashboard.TabItemColors
import majestic.users.dashboard.tools.ActiveView

@Composable
internal fun Tabs(
    pages: List<TabListItem>,
    colors: TabItemColors,
    active: ActiveView,
    modifier: Modifier = Modifier
) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(4.dp)
) {
    for (page in pages) Item(
        label = page.tab,
        colors = colors,
        selected = page.isSelected(active.view),
        onClick = {
            active.view = page.view
        }
    )
}