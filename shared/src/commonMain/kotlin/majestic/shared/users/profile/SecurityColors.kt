package majestic.shared.users.profile

import androidx.compose.ui.graphics.Color
import majestic.ColorPair
import majestic.TextFieldColors
import majestic.ToggleSwitchColors
import majestic.dialogs.DialogColors
import majestic.shared.tools.modal.ModalColors

data class SecurityColors(
    val background: Color,
    val foreground: Color,
    val constructive: Color,
    val destructive: Color,
    val solidConstructive: ColorPair,
    val modalColors: ModalColors,
    val dialogColors: DialogColors,
    val separator: Color,
    val field: TextFieldColors,
    val toggle: ToggleSwitchColors
)