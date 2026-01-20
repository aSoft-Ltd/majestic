package majestic.users.labels.profile.contact

import academia.users.labels.profile.contact.ActionsLabels

data class ContactLabels(
    val heading: String,
    val primary: String,
    val addButton: String,
    val availability: Availability,
    val actions: ActionsLabels,
    val forms: ContactActionFormLabels
)
data class ContactActionFormLabels(
    val phone: GenericContactFormLabels,
    val email: GenericContactFormLabels
)

data class Availability(
    val calls: String,
    val whatsapp: String,
)