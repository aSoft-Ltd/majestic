package majestic.shared.users.tools.colors.profile.security

import androidx.compose.runtime.Composable
import composex.screen.orientation.ScreenOrientation
import majestic.ThemeColor
import majestic.shared.tools.colors.ChangePasswordColors
import majestic.shared.tools.colors.rememberUserDialogColors
import majestic.shared.tools.colors.toBackground
import majestic.shared.tools.colors.toTextFieldColors
import majestic.shared.users.profile.PasswordFormColors
import majestic.shared.users.profile.SecurityColors
import majestic.shared.users.profile.TwoFactorColors

@Composable
fun ThemeColor.toSecurityColor(orientation: ScreenOrientation) = SecurityColors(
    theme = this,
    changePassword = ChangePasswordColors(
        theme = this,
        background = toBackground,
        passwordForm = PasswordFormColors(
            theme = this,
            field = toTextFieldColors()
        ),
        dialog = rememberUserDialogColors(orientation)
    ),
    twoFactor = TwoFactorColors(
        theme = this,
        toggle = toToggleSwitchColors()
    ),
    background = toBackground
)