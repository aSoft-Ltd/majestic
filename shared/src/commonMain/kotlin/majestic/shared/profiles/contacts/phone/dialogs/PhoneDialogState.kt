package majestic.shared.profiles.contacts.phone.dialogs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import majestic.shared.profiles.contacts.tools.dialogs.DialogType
import majestic.shared.profiles.contacts.tools.dialogs.GeneralDialogs
import majestic.shared.profiles.contacts.tools.dialogs.None

class PhoneDialogState(initial: GeneralDialogs = None) {
    var active by mutableStateOf(initial)

    val dialogType: DialogType? get() = active as? DialogType

    fun open(dialog: GeneralDialogs) {
        active = dialog
    }

    fun dismiss() {
        active = None
    }
}

@Composable
fun rememberPhoneDialogState(initial: GeneralDialogs = None) = remember {
    PhoneDialogState(initial)
}
