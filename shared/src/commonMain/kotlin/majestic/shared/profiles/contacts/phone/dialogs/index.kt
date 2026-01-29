package majestic.shared.profiles.contacts.phone.dialogs

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
import majestic.dialogs.DialogColors
import majestic.dialogs.Modal
import majestic.dialogs.flexible.FlexibleDialog
import majestic.shared.profiles.contacts.phone.Backgrounds
import majestic.shared.profiles.contacts.tools.dialogs.Bar
import majestic.shared.profiles.contacts.tools.dialogs.BarColors
import majestic.shared.profiles.contacts.tools.dialogs.Dialog
import majestic.shared.profiles.contacts.tools.dialogs.None
import majestic.shared.profiles.contacts.tools.dialogs.Prompt
import majestic.shared.users.label.contacts.ContactLabels

data class PhoneDialogsColors(
    val backgrounds: Backgrounds,
    val bar: BarColors,
    val content: PhoneDialogContentColors,
    val dialog: DialogColors,
)

@Composable
fun PhoneDialogs(
    state: PhoneDialogState,
    labels: ContactLabels,
    colors: PhoneDialogsColors,
    orientation: ScreenOrientation
) {

    if (state.active == None) return

    when (state.dialogType) {
        is Prompt -> Modal(
            colors = colors.dialog,
            onDismiss = { state.dismiss() },
            modifier = Modifier
                .then(if (orientation is Portrait) Modifier.padding(10.dp) else Modifier)
                .clip(RoundedCornerShape(20.dp))
                .background(colors.backgrounds.landscape)
        ) {
            PhoneDialogContent(
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
                    modifier = when (orientation) {
                        Landscape -> Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(vertical = 20.dp, horizontal = 16.dp)


                        Portrait -> Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .background(colors.backgrounds.landscape)
                            .padding(vertical = 5.dp, horizontal = 10.dp)
                    },
                    colors = colors.bar
                )
            },
        ) {
            PhoneDialogContent(
                labels = labels,
                colors = colors.content,
                orientation = orientation,
                state = state
            )
        }

        else -> {}
    }
}
