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
import majestic.button.Button
import majestic.button.appearence.securityTabItemButton
import majestic.button.basic.BasicButtonContent
import majestic.icons.Res
import majestic.icons.ic_laptop_remove
import majestic.layouts.Flex
import majestic.shared.users.label.profile.security.SecurityLabels
import majestic.shared.users.profile.SecurityColors

@Composable
internal fun ColumnScope.LogoutDevices(
    modifier: Modifier = Modifier,
    colors: SecurityColors,
    orientation: ScreenOrientation,
    labels: SecurityLabels,
) = SecurityRow(
    modifier = modifier,
    icon = Res.drawable.ic_laptop_remove,
    colors = colors,
    orientation = orientation,
) {
    SectionHeading(
        modifier = Modifier.then(if (orientation == Landscape) Modifier.weight(1f) else Modifier),
        labels = labels.logout,
        color = colors.foreground
    )
    Flex(
        modifier = Modifier.then(if (orientation == Landscape) Modifier.weight(1f) else Modifier),
        horizontalArrangement = Arrangement.End,
        orientation = orientation,
    ) {
        Button(
            modifier = Modifier.securityTabItemButton(
                color = colors.destructive,
                onClick = { }
            )
        ) { colors ->
            BasicButtonContent(
                text = labels.btnLogoutAll,
                colors = colors
            )
        }
        Spacer(modifier = Modifier.width(20.dp))
        Button(
            modifier = Modifier.securityTabItemButton(
                color = colors.constructive,
                onClick = { }
            )
        ) { colors ->
            BasicButtonContent(
                text = labels.btnLogout,
                colors = colors
            )
        }
    }
}