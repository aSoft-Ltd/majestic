package majestic.shared.users.profile

import androidx.compose.ui.graphics.Color
import majestic.ThemeColor
import majestic.shared.tools.colors.ChangePasswordColors

data class SecurityColors(
    val background: Color,
    val theme: ThemeColor,
    val changePassword: ChangePasswordColors,
    val twoFactor: TwoFactorColors,
)