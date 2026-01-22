package majestic.users.profile.security

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.ScreenOrientation
import majestic.ColorPair
import majestic.ThemeColor
import majestic.buttons.FlatButton
import majestic.buttons.FlatButtonColors
import majestic.dialogs.Flex
import majestic.icons.Res
import majestic.icons.ic_laptop_remove
import users.label.profile.security.SecurityLabels

data class LogoutDevicesColors(
    val theme: ThemeColor,
    val flatButton: FlatButtonColors
)

@Composable
internal fun ColumnScope.LogoutDevices(
    modifier: Modifier = Modifier,
    colors: LogoutDevicesColors,
    orientation: ScreenOrientation,
    labels: SecurityLabels,
) = SecurityRow(
    modifier = modifier,
    icon = Res.drawable.ic_laptop_remove,
    theme = colors.theme,
) {
    Flex(
        modifier = Modifier.padding(bottom = 30.dp),
        alignment = Alignment.CenterVertically,
        verticalArrangement = Arrangement.spacedBy(20.dp),
        orientation = orientation,
    ) {
        SectionHeading(
            modifier = Modifier.then(if (orientation == Landscape) Modifier.weight(1f) else Modifier),
            labels = labels.logout,
            color = colors.theme.surface.contra.color
        )
        Flex(
            modifier = Modifier.then(if (orientation == Landscape) Modifier.weight(1f) else Modifier),
            horizontalArrangement = Arrangement.End,
            orientation = orientation,
        ) {
            FlatButton(
                modifier = Modifier
                    .clip(CircleShape)
                    .border(1.dp, colors.theme.surface.contra.color.copy(0.1f), CircleShape),
                label = labels.btnLogoutAll,
                colors = colors.flatButton.copy(
                    inactive = ColorPair(
                        foreground = colors.theme.surface.contra.color.copy(alpha = 0.7f),
                        background = Color.Transparent
                    ),
                ),
            )
            Spacer(modifier = Modifier.width(20.dp))
            FlatButton(
                modifier = Modifier.clip(CircleShape),
                label = labels.btnLogout,
                colors = colors.flatButton
            )
        }
    }
}