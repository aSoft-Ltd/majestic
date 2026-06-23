package majestic.shared.credit.tools

import androidx.compose.ui.graphics.Color
import majestic.ColorPair
import majestic.buttons.IconButtonColors
import majestic.dropdown.DropdownColors
import majestic.shared.tools.menu.MenuOptionColors

data class CreditTableColors(
    val menu: MenuOptionColors,
    val icon: IconButtonColors,
    val dropdownColors: DropdownColors,
    val bgMenuColor: Color,
    val body: Color = Color.Transparent,
    val header: Color = Color.Transparent,
    val separator: Color = Color.Transparent,
    val surfaceColor: ColorPair,
)