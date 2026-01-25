package users.label.contacts

import profiles.contacts.tools.dialogs.ContactVerificationFormLabels

data class GenericContactFormLabels(
    val add: DedicatedFormLabels,
    val edit: DedicatedFormLabels,
    val dup: ContactPromptFormLabels,
    val delete: ContactPromptFormLabels,
    val verify: ContactVerificationFormLabels
)