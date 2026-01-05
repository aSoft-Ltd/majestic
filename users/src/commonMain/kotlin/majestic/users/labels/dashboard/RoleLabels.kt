package majestic.users.labels.dashboard

data class RoleLabels(
    val manage: String,
    val add: String,
    val actions: RoleActionsLabels
) {
    companion object {
        val english by lazy {
            RoleLabels(
                manage = "Manage",
                add = "Add",
                actions = RoleActionsLabels.english
            )
        }

        val swahili by lazy {
            RoleLabels(
                manage = "Simamia",
                add = "Ongeza",
                actions = RoleActionsLabels.swahili
            )
        }
    }

}
