package majestic.users.profile.security

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.ScreenOrientation
import majestic.ThemeColor
import majestic.icons.Res
import majestic.icons.ic_laptop_remove
import majestic.layouts.Flex
import majestic.shared.button.Button
import majestic.shared.users.label.profile.security.SecurityLabels

@Composable
internal fun ColumnScope.LogoutDevices(
    modifier: Modifier = Modifier,
    theme: ThemeColor,
    orientation: ScreenOrientation,
    labels: SecurityLabels,
) = SecurityRow(
    modifier = modifier,
    icon = Res.drawable.ic_laptop_remove,
    theme = theme,
    orientation = orientation,
) {
    SectionHeading(
        modifier = Modifier.then(if (orientation == Landscape) Modifier.weight(1f) else Modifier),
        labels = labels.logout,
        color = theme.surface.contra.color
    )
    Flex(
        modifier = Modifier.then(if (orientation == Landscape) Modifier.weight(1f) else Modifier),
        horizontalArrangement = Arrangement.End,
        orientation = orientation,
    ) {
        Button(
            onClick = {},
            label = labels.btnLogoutAll,
            theme = theme
        )
        Spacer(modifier = Modifier.width(20.dp))
        Button(
            onClick = {},
            label = labels.btnLogout,
            theme = theme
        )
    }
}