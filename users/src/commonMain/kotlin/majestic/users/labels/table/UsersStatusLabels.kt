package majestic.users.labels.table

data class UsersStatusLabels(
    val invited: String,
    val active: String,
    val declined: String,
    val revoked: String
) {
    companion object {
        val english by lazy {
            UsersStatusLabels(
                invited = "Invited",
                active = "Active",
                declined = "Declined",
                revoked = "Revoked"
            )
        }
        val swahili by lazy {
            UsersStatusLabels(
                invited = "Harikwa",
              active = "Hai",
              declined = "Imekataliwa",
              revoked = "Imefutwa"
            )
        }
    }
}