package users.label.contacts


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