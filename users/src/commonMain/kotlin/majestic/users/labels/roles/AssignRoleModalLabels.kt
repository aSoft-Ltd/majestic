package majestic.users.labels.roles

data class AssignRoleModalLabels(
    val title: String,
    val assignSelected: String,
    val cancel: String,
    val searchPlaceholder: String
) {
    companion object {
        val english by lazy {
            AssignRoleModalLabels(
                title = "Assign Roles to",
                assignSelected = "Assign Selected",
                cancel = "Cancel",
                searchPlaceholder = "Search roles..."
            )
        }

        val swahili by lazy {
            AssignRoleModalLabels(
                title = "Kabidhisha Majukumu kwa",
                assignSelected = "Kabidhisha Vilichaguliwa",
                cancel = "Ghairi",
                searchPlaceholder = "Tafuta majukumu..."
            )
        }
    }
}