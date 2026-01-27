package majestic.shared.users.label.contacts

data class ActionsLabels(
    val primary: String,
    val email: Particular,
    val phone: Particular
)

data class Particular(
    val edit: String,
    val duplicate: String,
    val delete: String,
)