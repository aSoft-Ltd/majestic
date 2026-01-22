package profiles.contacts.tools.dialogs

internal sealed interface GeneralDialogs : DialogType
internal object Delete : GeneralDialogs, Prompt
internal object Add : GeneralDialogs, Dialog
internal object Verify : GeneralDialogs, Dialog
internal object Edit : GeneralDialogs, Dialog
internal object Duplicate : GeneralDialogs, Prompt
internal object None : GeneralDialogs
