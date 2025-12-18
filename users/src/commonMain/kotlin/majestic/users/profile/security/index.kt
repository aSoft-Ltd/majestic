package majestic.users.profile.security

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import captain.Navigator
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.ThemeColor
import majestic.users.labels.settings.LanguageController
import majestic.users.labels.settings.observeUsersLabels
import majestic.users.tools.ProfilePortraitHeader
import majestic.users.tools.ProfilePortraitHeaderColors
import majestic.users.tools.data.separator

data class SecurityColors(
    val background: Color,
    val theme: ThemeColor,
    val changePassword: ChangePasswordColors,
    val logoutDevices: LogoutDevicesColors,
    val profileHeader: ProfilePortraitHeaderColors,
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
    navigator: Navigator,
    orientation: ScreenOrientation,
    language: LanguageController
) {
    val theme = colors.theme

    val labels by observeUsersLabels(language)
    Column(
        modifier = Modifier.background(
            color = if (orientation is Landscape) colors.background.copy(.5f) else Color.Transparent,
            shape = RoundedCornerShape(20.dp)
        )
    ) {
        if (orientation is Portrait) ProfilePortraitHeader(
            title = "",
            navigator = navigator,
            colors = colors.profileHeader,
        )

        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            ChangePassword(
                modifier = Modifier.security(theme = theme),
                orientation = orientation,
                labels = labels.profile.security,
                colors = colors.changePassword
            )
            LogoutDevices(
                modifier = Modifier.security(theme = theme),
                orientation = orientation,
                labels = labels.profile.security,
                colors = colors.logoutDevices
            )
            TwoFactor(
                modifier = Modifier.security(theme = theme),
                orientation = orientation,
                labels = labels.profile.security,
                colors = colors.twoFactor
            )
            DeleteAccount(
                modifier = Modifier.padding(top = 30.dp),
                theme = theme,
                orientation = orientation,
                labels = labels.profile.security,
            )
        }
    }
}