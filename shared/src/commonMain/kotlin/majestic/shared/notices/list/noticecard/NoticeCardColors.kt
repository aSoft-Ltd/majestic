package majestic.shared.notices.list.noticecard

import androidx.compose.ui.graphics.Color
import majestic.ColorPair
import majestic.dropdown.DropdownColors

data class NoticeStatusColors(
    val posting: Color,
    val posted: Color,
    val failed: Color,
    val draft: Color
)

data class NoticeCardColors(
    val background: Color,
    val icon: ColorPair,
    val title: Color,
    val divider: Color,
    val separator: Color,
    val info: Color,
    val description: Color,
    val tag: ColorPair,
    val dropdown: DropdownColors,
    val status: NoticeStatusColors
)
