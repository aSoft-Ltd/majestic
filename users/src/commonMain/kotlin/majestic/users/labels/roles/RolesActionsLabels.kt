package majestic.users.labels.roles

data class RolesActionsLabels(
    val addRole: String,
    val viewRole: String,
    val editRole: String,
    val deleteRole: String
) {
    companion object {
        val english by lazy {
            RolesActionsLabels(
                addRole = "Add Role",
                viewRole = "View Role",
                editRole = "Edit Role",
                deleteRole = "Delete Role"
            )
        }

        val swahili by lazy {
            RolesActionsLabels(
                addRole = "Ongeza Jukumu",
                viewRole = "Angalia Jukumu",
                editRole = "Hariri Jukumu",
                deleteRole = "Futa Jukumu"
            )
        }
    }
}