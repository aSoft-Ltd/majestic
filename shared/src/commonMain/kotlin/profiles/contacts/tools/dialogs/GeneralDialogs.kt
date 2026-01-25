package profiles.contacts.tools.dialogs

sealed interface GeneralDialogs : DialogType
object Delete : GeneralDialogs, Prompt
object Add : GeneralDialogs, Dialog
object Verify : GeneralDialogs, Dialog
object Edit : GeneralDialogs, Dialog
object Duplicate : GeneralDialogs, Prompt
object None : GeneralDialogs
