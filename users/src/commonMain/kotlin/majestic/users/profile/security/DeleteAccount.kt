package majestic.users.profile.security

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import composex.screen.orientation.Landscape
import composex.screen.orientation.ScreenOrientation
import majestic.ColorPair
import majestic.ThemeColor
import majestic.buttons.FlatButton
import majestic.buttons.FlatButtonColors
import majestic.icons.Res
import majestic.icons.ic_user_remove
import majestic.shared.profiles.contacts.tools.buttons.flatButton
import majestic.shared.profiles.contacts.tools.buttons.toBackgroundColor
import majestic.shared.users.label.profile.security.SecurityLabels
import majestic.tooling.onClick


internal fun toDeleteButtonColors(): FlatButtonColors = FlatButtonColors(
    hovered = ColorPair(
        foreground = Color(0xFFEF5350),
        background = Color(0xFFEF5350).copy(alpha = 0.15f)
    ),
    inactive = ColorPair(
        foreground = Color(0xFFEF5350).copy(alpha = 0.7f),
        background = Color(0xFFEF5350).copy(alpha = 0.07f)
    ),
)

@Composable
internal fun ColumnScope.DeleteAccount(
    modifier: Modifier = Modifier,
    theme: ThemeColor,
    orientation: ScreenOrientation,
    labels: SecurityLabels,
) = SecurityRow(
    modifier = modifier,
    icon = Res.drawable.ic_user_remove,
    theme = theme,
    orientation = orientation,
) {
    SectionHeading(
        modifier = Modifier.then(if (orientation == Landscape) Modifier.weight(2f) else Modifier),
        labels = labels.deleteAccount,
        color = theme.surface.contra.color
    )
    Row(
        modifier = Modifier.then(if (orientation == Landscape) Modifier.weight(1f) else Modifier),
        horizontalArrangement = Arrangement.End,
    ) {
        val interactionSource = remember { MutableInteractionSource() }
        val isHovered by interactionSource.collectIsHoveredAsState()
        FlatButton(
            modifier = Modifier
                .flatButton(
                    interactionSource = interactionSource,
                    bgColor = toDeleteButtonColors().toBackgroundColor(isHovered = isHovered),
                )
                .onClick {},
            label = labels.btnDelete,
            colors = toDeleteButtonColors(),
            interactionSource = interactionSource,
        )
    }
}