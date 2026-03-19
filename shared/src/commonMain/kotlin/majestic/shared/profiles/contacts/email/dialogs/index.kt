package majestic.shared.profiles.contacts.email.dialogs

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import composex.screen.orientation.ScreenOrientation
import majestic.dialogs.DialogColors
import majestic.dialogs.flexible.FlexibleDialog
import majestic.shared.profiles.contacts.phone.Backgrounds
import majestic.shared.profiles.contacts.tools.dialogs.BarColors
import majestic.shared.profiles.contacts.tools.dialogs.None
import majestic.shared.tools.modal.ModalColors
import majestic.shared.tools.modal.ModalHeader
import majestic.shared.tools.modal.modalHeaderStyle
import majestic.shared.tools.modal.modalPopupStyle
import majestic.shared.users.label.contacts.ContactLabels

data class EmailDialogsColors(
    val backgrounds: Backgrounds,
    val dialog: DialogColors,
    val bar: BarColors,
    val content: EmailDialogContentColors,
    val modalColors: ModalColors
)

@Composable
fun EmailDialogs(
    state: EmailDialogState,
    labels: ContactLabels,
    colors: EmailDialogsColors,
    orientation: ScreenOrientation,
) {

    if (state.active == None) return

    FlexibleDialog(
        onDismiss = { state.dismiss() },
        modifier = Modifier.modalPopupStyle(orientation, colors.modalColors),
        bar = {
            ModalHeader(
                title = state.active.getTitle(labels),
                onClose = { state.dismiss() },
                colors = colors.modalColors,
                orientation = orientation,
                modifier = Modifier.modalHeaderStyle(colors = colors.modalColors)
            )
        },
    ) {
        EmailDialogContent(
            orientation = orientation,
            state = state,
            labels = labels,
            colors = colors.content
        )
    }
}