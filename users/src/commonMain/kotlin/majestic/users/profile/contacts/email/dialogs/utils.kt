package majestic.users.profile.contacts.email.dialogs

import majestic.users.labels.profile.contact.ContactLabels
import majestic.users.profile.contacts.tools.dialogs.Delete
import majestic.users.profile.contacts.tools.dialogs.Duplicate
import majestic.users.profile.contacts.tools.dialogs.Edit
import majestic.users.profile.contacts.tools.dialogs.GeneralDialogs
import majestic.users.profile.contacts.tools.dialogs.Verify
import majestic.users.profile.contacts.tools.GeneralPromptLabels

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
    is Verify -> labels.forms.email.verify.title
    is Duplicate -> labels.forms.email.dup.title
    is Edit -> labels.forms.email.edit.title
    is Delete -> labels.forms.email.delete.title
    else -> ""
}