package majestic.users.profile.contacts.email.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.users.labels.profile.contact.ContactLabels
import majestic.users.profile.contacts.phone.Backgrounds
import majestic.users.profile.contacts.tools.dialogs.Bar
import majestic.users.profile.contacts.tools.dialogs.BarColors
import majestic.users.profile.contacts.tools.dialogs.Dialog
import majestic.users.profile.contacts.tools.dialogs.None
import majestic.users.profile.contacts.tools.dialogs.Prompt
import majestic.users.tools.dialogs.DialogColors
import majestic.users.tools.dialogs.FlexibleDialog
import majestic.users.tools.dialogs.Modal

data class EmailDialogsColors(
    val backgrounds: Backgrounds,
    val dialog: DialogColors,
    val bar: BarColors,
    val content: EmailDialogContentColors
)

@Composable
internal fun EmailDialogs(
    state: EmailDialogState,
    labels: ContactLabels,
    colors: EmailDialogsColors,
    orientation: ScreenOrientation
) {

    if (state.active == None) return

    when (state.dialogType) {
        is Prompt -> Modal(
            colors = colors.dialog,
            onDismiss = { state.dismiss() },
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(colors.backgrounds.landscape)
        ) {
            EmailDialogContent(
                orientation = orientation,
                state = state,
                labels = labels,
                colors = colors.content
            )
        }

        is Dialog -> FlexibleDialog(
            modifier = when (orientation) {
                Landscape -> Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(colors.backgrounds.landscape)

                Portrait -> Modifier
                    .fillMaxSize()
                    .background(colors.backgrounds.portrait)
            },
            colors = colors.dialog,
            onDismiss = { state.dismiss() },
            orientation = orientation,
            bar = {
                Bar(
                    title = state.active.getTitle(labels),
                    onDismiss = { state.dismiss() },
                    orientation = orientation,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(vertical = 20.dp, horizontal = 16.dp),
                    colors = colors.bar
                )
            },
        ) {
            EmailDialogContent(
                labels = labels,
                colors = colors.content,
                orientation = orientation,
                state = state
            )
        }

        else -> {}
    }
}
