package profiles.contacts.phone.dialogs

import profiles.contacts.tools.dialogs.Add
import profiles.contacts.tools.dialogs.Delete
import profiles.contacts.tools.dialogs.Duplicate
import profiles.contacts.tools.dialogs.Edit
import profiles.contacts.tools.dialogs.GeneralDialogs
import profiles.contacts.tools.dialogs.Verify
import users.label.contacts.ContactLabels
import users.label.contacts.ContactPromptFormLabels

internal fun ContactLabels.toPhoneDeleteLabels() = ContactPromptFormLabels(
    title = forms.phone.delete.title,
    description = forms.phone.delete.description,
    contact = "",
    info = forms.phone.delete.info,
    submit = forms.phone.delete.submit,
    cancel = forms.phone.delete.cancel,
)

internal fun ContactLabels.toPhoneDuplicateLabels() = ContactPromptFormLabels(
    title = forms.phone.dup.title,
    description = forms.phone.dup.description,
    contact = "",
    info = forms.phone.dup.info,
    submit = forms.phone.dup.submit,
    cancel = forms.phone.dup.cancel,
)

internal fun GeneralDialogs.getTitle(labels: ContactLabels): String = when (this) {
    is Add -> labels.forms.phone.add.title
    is Verify -> labels.forms.phone.verify.title
    is Duplicate -> labels.forms.phone.dup.title
    is Edit -> labels.forms.phone.edit.title
    is Delete -> labels.forms.phone.delete.title
    else -> ""
}