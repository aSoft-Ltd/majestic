package majestic.users.labels.dashboard

data class RoleLabels(
    val title: String,
    val manage: String,
    val add: String,
    val actions: RoleActionsLabels,
    val permissions: String,
    val assigned: String
) {
    companion object {
        val english by lazy {
            RoleLabels(
                title = "Roles",
                manage = "Manage",
                add = "Add",
                actions = RoleActionsLabels.english,
                permissions = "Permissions",
                assigned = "Assigned"
            )
        }

        val swahili by lazy {
            RoleLabels(
                title = "Wajibu",
                manage = "Simamia",
                add = "Ongeza",
                actions = RoleActionsLabels.swahili,
                permissions = "Ruhusa",
                assigned = "Pangiwa"
            )
        }
    }

}
