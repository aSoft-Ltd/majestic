package majestic.users.labels.profile.contact

data class GenericContactFormLabels(
    val add: majestic.users.labels.profile.contact.DedicatedFormLabels,
    val edit: majestic.users.labels.profile.contact.DedicatedFormLabels,
    val dup: majestic.users.labels.profile.contact.ContactPromptFormLabels,
    val delete: majestic.users.labels.profile.contact.ContactPromptFormLabels,
    val verify: majestic.users.labels.profile.contact.ContactVerificationFormLabels
)