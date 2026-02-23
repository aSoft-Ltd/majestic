package majestic.payments.tools.table

import androidx.compose.ui.graphics.Color
import majestic.CheckboxColors
import majestic.ColorPair
import majestic.dropdown.DropdownColors
import majestic.payments.tools.menu.MenuOptionColors

data class TableColors(
    val background: Color,
    val headerBackground: Color,
    val foreground: Color,
    val separator: Color,
    val hovered: Color,
    val icon: Color,
    val header: ColorPair,
    val checkbox: CheckboxColors,
    val dropdown: DropdownColors
)