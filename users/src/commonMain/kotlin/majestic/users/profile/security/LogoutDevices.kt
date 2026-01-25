package majestic.users.profile.security

import Flex
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.ScreenOrientation
import majestic.ColorPair
import majestic.ThemeColor
import majestic.buttons.FlatButton
import majestic.buttons.FlatButtonColors
import majestic.icons.Res
import majestic.icons.ic_laptop_remove
import profiles.contacts.tools.buttons.flatButton
import profiles.contacts.tools.buttons.getBackground
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
        val logoutInteraction = remember { MutableInteractionSource() }
        val logoutHovered by logoutInteraction.collectIsHoveredAsState()
        val logoutAllInteraction = remember { MutableInteractionSource() }
        val logoutAllHovered by logoutAllInteraction.collectIsHoveredAsState()

        FlatButton(
            modifier = Modifier.flatButton(
                interactionSource = logoutAllInteraction,
                bgColor = colors.flatButton.copy(
                    inactive = ColorPair(
                        foreground = colors.theme.surface.contra.color.copy(alpha = 0.7f),
                        background = Color.Transparent
                    ),
                ).getBackground(isHovered = logoutAllHovered),
            ),
            label = labels.btnLogoutAll,
            colors = colors.flatButton.copy(
                inactive = ColorPair(
                    foreground = colors.theme.surface.contra.color.copy(alpha = 0.7f),
                    background = Color.Transparent
                ),
            ),
            interactionSource = logoutAllInteraction,
        )
        Spacer(modifier = Modifier.width(20.dp))
        FlatButton(
            modifier = Modifier
                .flatButton(
                    interactionSource = logoutInteraction,
                    bgColor = colors.flatButton.getBackground(isHovered = logoutHovered),
                ),
            label = labels.btnLogout,
            colors = colors.flatButton,
            interactionSource = logoutInteraction,
        )
    }
}