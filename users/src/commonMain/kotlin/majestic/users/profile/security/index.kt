package majestic.users.profile.security

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import composex.screen.orientation.ScreenOrientation
import majestic.shared.users.label.profile.security.SecurityLabels
import majestic.shared.users.profile.SecurityColors
import majestic.users.tools.data.separator

private fun Modifier.security(separator: Color, lastItem: Boolean = false) = this
    .padding(top = 30.dp)
    .separator(
        isLast = lastItem,
        color = separator
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
    ChangePassword(
        modifier = Modifier.security(colors.separator),
        orientation = orientation,
        labels = labels,
        colors = colors
    )
    LogoutDevices(
        modifier = Modifier.security(colors.separator),
        orientation = orientation,
        labels = labels,
        colors = colors
    )
    TwoFactor(
        modifier = Modifier.security(colors.separator),
        orientation = orientation,
        labels = labels,
        colors = colors
    )
    DeleteAccount(
        modifier = Modifier.padding(top = 30.dp),
        orientation = orientation,
        labels = labels,
        colors = colors
    )
}