package majestic.shared.profiles.contacts.phone.dialogs

import majestic.shared.profiles.contacts.tools.dialogs.Add
import majestic.shared.profiles.contacts.tools.dialogs.Delete
import majestic.shared.profiles.contacts.tools.dialogs.Duplicate
import majestic.shared.profiles.contacts.tools.dialogs.Edit
import majestic.shared.profiles.contacts.tools.dialogs.GeneralDialogs
import majestic.shared.profiles.contacts.tools.dialogs.Verify
import majestic.shared.users.label.contacts.ContactLabels
import majestic.shared.users.label.contacts.ContactPromptFormLabels

internal fun ContactLabels.toPhoneDeleteLabels() = ContactPromptFormLabels(
    title = forms.phone.delete.title,
    description = forms.phone.delete.description,
    info = forms.phone.delete.info,
    submit = forms.phone.delete.submit,
    cancel = forms.phone.delete.cancel,
)

internal fun ContactLabels.toPhoneDuplicateLabels() = ContactPromptFormLabels(
    title = forms.phone.dup.title,
    description = forms.phone.dup.description,
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