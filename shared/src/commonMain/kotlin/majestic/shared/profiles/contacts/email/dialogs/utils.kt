package majestic.shared.profiles.contacts.email.dialogs

import majestic.shared.profiles.contacts.tools.dialogs.Add
import majestic.shared.profiles.contacts.tools.dialogs.Delete
import majestic.shared.profiles.contacts.tools.dialogs.Duplicate
import majestic.shared.profiles.contacts.tools.dialogs.Edit
import majestic.shared.profiles.contacts.tools.dialogs.GeneralDialogs
import majestic.shared.profiles.contacts.tools.dialogs.None
import majestic.shared.profiles.contacts.tools.dialogs.Verify
import majestic.shared.users.label.contacts.ContactLabels
import majestic.shared.users.label.contacts.ContactPromptFormLabels

internal fun ContactLabels.toEmailDeleteLabels() = ContactPromptFormLabels(
    title = forms.email.delete.title,
    description = forms.email.delete.description,
    contact = "",
    info = forms.email.delete.info,
    submit = forms.email.delete.submit,
    cancel = forms.email.delete.cancel,
)

internal fun ContactLabels.toEmailDuplicateLabels() = ContactPromptFormLabels(
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