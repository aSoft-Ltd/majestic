package majestic.shared.tools.table

import androidx.compose.ui.graphics.Color
import majestic.CheckboxColors
import majestic.dropdown.DropdownColors

data class TableColors(
    val header: Color,
    val body: Color,
    val headerBorder: Color,
    val bodyBorder: Color,
    val active: Color,
    val hovered: Color,
    val checkbox: CheckboxColors,
    val dropdown: DropdownColors,
    val rowIcon: Color,
    val foreground: Color,
)