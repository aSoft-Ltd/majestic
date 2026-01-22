package profiles.contacts.email.dialogs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import profiles.contacts.tools.dialogs.DialogType
import profiles.contacts.tools.dialogs.GeneralDialogs
import profiles.contacts.tools.dialogs.None

internal class EmailDialogState(initial: GeneralDialogs = None) {
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
internal fun rememberEmailDialogState(initial: GeneralDialogs = None) = remember {
    EmailDialogState(initial)
}
