package majestic.shared.tools.colors

import androidx.compose.ui.graphics.Color
import majestic.ThemeColor
import majestic.dialogs.DialogColors
import majestic.shared.users.profile.PasswordFormColors

data class ChangePasswordColors(
    val background: Color,
    val theme: ThemeColor,
    val passwordForm: PasswordFormColors,
    val dialog: DialogColors
)