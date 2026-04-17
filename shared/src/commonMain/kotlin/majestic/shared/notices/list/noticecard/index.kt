package majestic.shared.notices.list.noticecard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.ScreenOrientation
import majestic.dropdown.DropdownItem
import majestic.shared.notices.list.NoticeItem

@Composable
fun <T> NoticeCard(
    item: NoticeItem,
    labels: NoticeCardLabels,
    colors: NoticeCardColors,
    orientation: ScreenOrientation,
    actions: List<DropdownItem<T>>,
    onAction: (T) -> Unit,
    modifier: Modifier = Modifier
) = Column(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(16.dp)
) {
    Header(
        item = item,
        labels = labels,
        colors = colors,
        actions = actions,
        onAction = onAction,
        orientation = orientation,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = if (orientation is Landscape) 12.dp else 10.dp)
    )
    if (orientation == Landscape) {
        Content(
            item = item,
            colors = colors,
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 12.dp)
        )
        Footer(
            item = item,
            labels = labels,
            colors = colors,
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp, horizontal = 12.dp)
        )
    }
}