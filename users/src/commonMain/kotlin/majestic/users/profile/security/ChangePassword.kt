package majestic.users.profile.security

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.ScreenOrientation
import majestic.dialogs.Modal
import majestic.icons.Res
import majestic.icons.ic_square_lock
import majestic.shared.button.Button
import majestic.shared.users.label.profile.security.SecurityLabels
import majestic.shared.users.profile.ChangePasswordColors


@Composable
internal fun ColumnScope.ChangePassword(
    modifier: Modifier = Modifier,
    colors: ChangePasswordColors,
    orientation: ScreenOrientation,
    labels: SecurityLabels,
) = SecurityRow(
    modifier = modifier,
    icon = Res.drawable.ic_square_lock,
    theme = colors.theme,
    orientation = orientation,
) {
    var modalOpened by remember { mutableStateOf(false) }
    if (modalOpened) Modal(
        modifier = Modifier.background(colors.dialog.containerColor).padding(20.dp),
        colors = colors.dialog,
        onDismiss = { modalOpened = false },
    ) {
        PasswordForm(
            modifier = Modifier.padding(vertical = 40.dp, horizontal = 30.dp),
            colors = colors.passwordForm,
            labels = labels.forms.password,
            onSubmit = { modalOpened = false }
        )
    }


    SectionHeading(
        modifier = Modifier.then(if (orientation == Landscape) Modifier.weight(2f) else Modifier),
        labels = labels.password,
        color = colors.theme.surface.contra.color
    )
    Row(
        modifier = Modifier.then(if (orientation == Landscape) Modifier.weight(1f) else Modifier),
        horizontalArrangement = Arrangement.End,
    ) {
        Button(
            onClick = { modalOpened = true },
            label = labels.btnNewPassword,
            theme = colors.theme
        )
    }
}