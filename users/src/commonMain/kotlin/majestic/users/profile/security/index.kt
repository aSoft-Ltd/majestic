package majestic.users.profile.security

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import composex.screen.orientation.ScreenOrientation
import majestic.ThemeColor
import majestic.shared.users.label.profile.security.SecurityLabels
import majestic.shared.users.profile.SecurityColors
import majestic.users.tools.data.separator


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
    labels: SecurityLabels
) = Column(
    modifier = modifier
) {
    val theme = colors.theme

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
        theme = theme
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