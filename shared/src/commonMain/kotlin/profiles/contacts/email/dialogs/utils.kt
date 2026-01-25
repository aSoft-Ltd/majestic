package profiles.contacts.email.dialogs

import profiles.contacts.tools.dialogs.GeneralPromptLabels
import profiles.contacts.tools.dialogs.Add
import profiles.contacts.tools.dialogs.Delete
import profiles.contacts.tools.dialogs.Duplicate
import profiles.contacts.tools.dialogs.Edit
import profiles.contacts.tools.dialogs.GeneralDialogs
import profiles.contacts.tools.dialogs.None
import profiles.contacts.tools.dialogs.Verify
import users.label.contacts.ContactLabels

internal fun ContactLabels.toEmailDeleteLabels(): GeneralPromptLabels = GeneralPromptLabels(
    title = forms.email.delete.title,
    description = forms.email.delete.description,
    contact = "",
    info = forms.email.delete.info,
    submit = forms.email.delete.submit,
    cancel = forms.email.delete.cancel,
)

internal fun ContactLabels.toEmailDuplicateLabels() = GeneralPromptLabels(
    title = forms.email.dup.title,
    description = forms.email.dup.description,
    contact = "",
    info = forms.email.dup.info,
    submit = forms.email.dup.submit,
    cancel = forms.email.dup.cancel,
)

internal fun GeneralDialogs.getTitle(labels: ContactLabels): String = when (this) {
    is Add -> labels.forms.email.add.title
    is Verify -> labels.forms.email.verify.title
    is Duplicate -> labels.forms.email.dup.title
    is Edit -> labels.forms.email.edit.title
    is Delete -> labels.forms.email.delete.title
    is None -> ""
}