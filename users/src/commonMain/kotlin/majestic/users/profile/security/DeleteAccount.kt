package majestic.users.profile.security

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import composex.screen.orientation.Landscape
import composex.screen.orientation.ScreenOrientation
import majestic.button.Button
import majestic.button.appearence.securityTabItemButton
import majestic.button.basic.BasicButtonContent
import majestic.icons.Res
import majestic.icons.ic_user_remove
import majestic.shared.users.label.profile.security.SecurityLabels
import majestic.shared.users.profile.SecurityColors

@Composable
internal fun ColumnScope.DeleteAccount(
    modifier: Modifier = Modifier,
    colors: SecurityColors,
    orientation: ScreenOrientation,
    labels: SecurityLabels,
) = SecurityRow(
    modifier = modifier,
    icon = Res.drawable.ic_user_remove,
    colors = colors,
    orientation = orientation,
) {
    SectionHeading(
        modifier = Modifier.then(if (orientation == Landscape) Modifier.weight(2f) else Modifier),
        labels = labels.deleteAccount,
        color = colors.foreground
    )
    Row(
        modifier = Modifier.then(if (orientation == Landscape) Modifier.weight(1f) else Modifier),
        horizontalArrangement = Arrangement.End,
    ) {
        Button(
            modifier = Modifier.securityTabItemButton(
                color = colors.destructive,
                onClick = { }
            )
        ) { colors ->
            BasicButtonContent(
                text = labels.btnDelete,
                colors = colors
            )
        }
    }
}