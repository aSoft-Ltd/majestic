package majestic.users.profile.security

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
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
import majestic.icons.ic_user_remove
import users.label.profile.security.SecurityLabels

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
) {
    Flex(
        modifier = Modifier.padding(bottom = 30.dp),
        alignment = Alignment.CenterVertically,
        verticalArrangement = Arrangement.spacedBy(20.dp),
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
            FlatButton(
                modifier = Modifier.clip(CircleShape),
                label = labels.btnDelete,
                colors = FlatButtonColors(
                    hovered = ColorPair(
                        foreground = Color(0xFFEF5350),
                        background = Color(0xFFEF5350).copy(alpha = 0.15f)
                    ),
                    inactive = ColorPair(
                        foreground = Color(0xFFEF5350).copy(alpha = 0.7f),
                        background = Color(0xFFEF5350).copy(alpha = 0.07f)
                    ),
                ),
            )
        }
    }
}