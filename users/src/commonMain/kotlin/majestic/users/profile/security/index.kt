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

@Composable
fun Security(
    colors: SecurityColors,
    navigator: Navigator,
    orientation: ScreenOrientation,
    language: LanguageController
) {
    val theme = colors.theme

    val labels by observeUsersLabels(language)
    val rowModifier = Modifier.padding(top = 30.dp).separator(
        true,
        color = theme.surface.contra.color.copy(0.03f)
    )

    Column {
        if (orientation is Portrait) ProfilePortraitHeader(
            title = "",
            navigator = navigator,
            colors = colors.profileHeader,
        )

        Column(
            modifier = Modifier
                .background(colors.background, RoundedCornerShape(20.dp))
                .verticalScroll(rememberScrollState())
        ) {
            ChangePassword(
                modifier = rowModifier,
                orientation = orientation,
                labels = labels.profile.security,
                colors = colors.changePassword
            )
            LogoutDevices(
                modifier = rowModifier,
                orientation = orientation,
                labels = labels.profile.security,
                colors = colors.logoutDevices
            )
            TwoFactor(
                modifier = rowModifier,
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