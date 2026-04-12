package majestic.shared.notices.list

import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.dropdown.DropdownItem
import majestic.shared.notices.list.noticecard.NoticeCard
import majestic.shared.notices.list.noticecard.NoticeCardColors
import majestic.shared.notices.list.noticecard.NoticeCardLabels
import majestic.shared.tools.rememberHoverBackground
import majestic.shared.tools.separator

@Composable
fun <T> NoticeList(
    items: List<NoticeItem>,
    labels: NoticeCardLabels,
    orientation: ScreenOrientation,
    colors: NoticeCardColors,
    actions: (NoticeItem) -> List<DropdownItem<T>>,
    onAction: (NoticeItem, T) -> Unit,
    modifier: Modifier = Modifier
) = LazyColumn(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(if (orientation is Landscape) 16.dp else 0.dp)
) {

    items(items) { item ->
        val (background, interaction) = rememberHoverBackground(
            background = colors.background,
            foreground = colors.dropdown.itemsDefault
        )
        NoticeCard(
            item = item,
            labels = labels,
            colors = colors,
            orientation = orientation,
            actions = actions(item),
            onAction = { onAction(item, it) },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(if (orientation is Landscape) 16.dp else 0.dp))
                .background(color = background)
                .then(
                    when (orientation) {
                        is Portrait -> Modifier.separator(colors.separator)
                        is Landscape -> Modifier
                            .hoverable(interactionSource = interaction)
                            .pointerHoverIcon(PointerIcon.Hand)
                    }
                )
                .padding(8.dp)
        )
    }
}
