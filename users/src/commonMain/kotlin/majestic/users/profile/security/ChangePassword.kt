package majestic.users.profile.security

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.button.Button
import majestic.button.appearence.securityTabItemButton
import majestic.button.basic.BasicButtonContent
import majestic.dialogs.Modal
import majestic.icons.Res
import majestic.icons.ic_square_lock
import majestic.shared.users.label.profile.security.SecurityLabels
import majestic.shared.users.profile.SecurityColors

@Composable
internal fun ColumnScope.ChangePassword(
    modifier: Modifier = Modifier,
    colors: SecurityColors,
    orientation: ScreenOrientation,
    labels: SecurityLabels,
) = SecurityRow(
    modifier = modifier,
    icon = Res.drawable.ic_square_lock,
    colors = colors,
    orientation = orientation,
) {
    var modalOpened by remember { mutableStateOf(false) }
    if (modalOpened) Modal(
        modifier = Modifier
            .then(if (orientation == Portrait) Modifier.fillMaxHeight() else Modifier.clip(RoundedCornerShape(20.dp)))
            .background(colors.modalColors.body),
        colors = colors.dialogColors,
        onDismiss = { modalOpened = false },
    ) {
        PasswordForm(
            modifier = Modifier.padding(vertical = 40.dp, horizontal = 30.dp),
            colors = colors,
            labels = labels.forms.password,
            onSubmit = { modalOpened = false }
        )
    }


    SectionHeading(
        modifier = Modifier.then(if (orientation == Landscape) Modifier.weight(2f) else Modifier),
        labels = labels.password,
        color = colors.foreground
    )
    Row(
        modifier = Modifier.then(if (orientation == Landscape) Modifier.weight(1f) else Modifier),
        horizontalArrangement = Arrangement.End,
    ) {
        Button(
            modifier = Modifier.securityTabItemButton(
                color = colors.constructive,
                onClick = { modalOpened = true }
            )
        ) { colors ->
            BasicButtonContent(
                text = labels.btnNewPassword,
                colors = colors
            )
        }
    }
}