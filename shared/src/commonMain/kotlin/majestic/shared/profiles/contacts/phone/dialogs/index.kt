package majestic.shared.profiles.contacts.phone.dialogs

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import composex.screen.orientation.ScreenOrientation
import majestic.dialogs.DialogColors
import majestic.dialogs.flexible.FlexibleDialog
import majestic.shared.profiles.contacts.email.dialogs.getTitle
import majestic.shared.profiles.contacts.phone.Backgrounds
import majestic.shared.profiles.contacts.tools.dialogs.BarColors
import majestic.shared.profiles.contacts.tools.dialogs.None
import majestic.shared.tools.modal.ModalColors
import majestic.shared.tools.modal.ModalHeader
import majestic.shared.tools.modal.modalHeaderStyle
import majestic.shared.tools.modal.modalPopupStyle
import majestic.shared.users.label.contacts.ContactLabels

data class PhoneDialogsColors(
    val backgrounds: Backgrounds,
    val bar: BarColors,
    val content: PhoneDialogContentColors,
    val dialog: DialogColors,
    val modalColors: ModalColors
)

@Composable
fun PhoneDialogs(
    state: PhoneDialogState,
    labels: ContactLabels,
    colors: PhoneDialogsColors,
    orientation: ScreenOrientation
) {

    if (state.active == None) return

    FlexibleDialog(
        orientation = orientation,
        onDismiss = { state.dismiss() },
        modifier = Modifier.modalPopupStyle(orientation, colors.modalColors),
        colors = colors.dialog,
        bar = {
            ModalHeader(
                title = state.active.getTitle(labels),
                onClose = { state.dismiss() },
                colors = colors.modalColors,
                modifier = Modifier.modalHeaderStyle(colors = colors.modalColors)
            )
        },
    ) {
        PhoneDialogContent(
            orientation = orientation,
            state = state,
            labels = labels,
            colors = colors.content
        )
    }
}