package majestic.users.profile.contacts.phone.dialogs

import majestic.users.labels.profile.contact.ContactLabels
import majestic.users.profile.contacts.tools.GeneralPromptLabels
import majestic.users.profile.contacts.tools.dialogs.Add
import majestic.users.profile.contacts.tools.dialogs.Delete
import majestic.users.profile.contacts.tools.dialogs.Duplicate
import majestic.users.profile.contacts.tools.dialogs.Edit
import majestic.users.profile.contacts.tools.dialogs.GeneralDialogs
import majestic.users.profile.contacts.tools.dialogs.Verify

internal fun ContactLabels.toPhoneDeleteLabels(): GeneralPromptLabels = GeneralPromptLabels(
    title = forms.phone.delete.title,
    description = forms.phone.delete.description,
    contact = "",
    info = forms.phone.delete.info,
    submit = forms.phone.delete.submit,
    cancel = forms.phone.delete.cancel,
)

internal fun ContactLabels.toPhoneDuplicateLabels() = GeneralPromptLabels(
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