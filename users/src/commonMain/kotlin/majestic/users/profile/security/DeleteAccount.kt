package majestic.users.profile.security

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import composex.screen.orientation.Landscape
import composex.screen.orientation.ScreenOrientation
import majestic.ThemeColor
import majestic.icons.Res
import majestic.icons.ic_user_remove
import majestic.shared.button.Button
import majestic.shared.button.ButtonVariant
import majestic.shared.users.label.profile.security.SecurityLabels

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
        Button(
            onClick = {},
            variant = ButtonVariant.Primary.Destructive,
            label = labels.btnDelete,
            theme = theme,
        )
    }
}