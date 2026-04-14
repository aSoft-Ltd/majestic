package majestic.shared.notices

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import majestic.dropdown.Dropdown
import majestic.dropdown.DropdownColors
import majestic.dropdown.DropdownItem
import majestic.icons.Res
import majestic.icons.ic_megaphone
import majestic.shared.notices.noticebar.DefaultNoticeBarHeader
import org.jetbrains.compose.resources.vectorResource

data class NoticeBarColors(
    val tint: Color,
    val title: Color,
    val action: Color,
    val dropdownColors: DropdownColors,

    )

@Composable
fun <T> NoticeBar(
    colors: NoticeBarColors,
    items: List<DropdownItem<T>> = emptyList(),
    onNotify: (T) -> Unit = {},
    notify: String,
    modifier: Modifier = Modifier,
    onClose: () -> Unit = {},
    header: @Composable () -> Unit = {
        DefaultNoticeBarHeader(colors, onClose)
    }
) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween
) {
    header()

    Dropdown(
        items = items,
        onAction = onNotify,
        colors = colors.dropdownColors,
        label = notify,
        leadingIcon = vectorResource(Res.drawable.ic_megaphone),
        modifier = Modifier.wrapContentWidth()
    )
}