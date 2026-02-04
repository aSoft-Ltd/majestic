package majestic.shared.users.profile

import androidx.compose.ui.graphics.Color
import majestic.ThemeColor

data class SecurityColors(
    val background: Color,
    val theme: ThemeColor,
    val changePassword: ChangePasswordColors,
    val twoFactor: TwoFactorColors,
)