package majestic.users.profile.security

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import composex.screen.orientation.ScreenOrientation
import majestic.ThemeColor
import majestic.users.labels.settings.LanguageController
import majestic.users.labels.settings.observeUsersLabels
import majestic.users.tools.data.separator

data class SecurityColors(
    val background: Color,
    val theme: ThemeColor,
    val changePassword: ChangePasswordColors,
    val logoutDevices: LogoutDevicesColors,
    val twoFactor: TwoFactorColors,
)

private fun Modifier.security(lastItem: Boolean = false, theme: ThemeColor) = this
    .padding(top = 30.dp)
    .separator(
        isLast = lastItem,
        color = theme.surface.contra.color.copy(0.05f)
    )


@Composable
fun Security(
    colors: SecurityColors,
    modifier: Modifier,
    orientation: ScreenOrientation,
    language: LanguageController
) = Column(
    modifier = modifier
) {
    val theme = colors.theme
    val language by observeUsersLabels(language)
    val labels = language.profile.tabs.security.content

    ChangePassword(
        modifier = Modifier.security(theme = theme),
        orientation = orientation,
        labels = labels,
        colors = colors.changePassword
    )
    LogoutDevices(
        modifier = Modifier.security(theme = theme),
        orientation = orientation,
        labels = labels,
        colors = colors.logoutDevices
    )
    TwoFactor(
        modifier = Modifier.security(theme = theme),
        orientation = orientation,
        labels = labels,
        colors = colors.twoFactor
    )
    DeleteAccount(
        modifier = Modifier.padding(top = 30.dp),
        theme = theme,
        orientation = orientation,
        labels = labels,
    )
}