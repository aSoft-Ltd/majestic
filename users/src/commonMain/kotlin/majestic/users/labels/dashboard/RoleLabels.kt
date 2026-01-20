package majestic.users.labels.dashboard

data class RoleLabels(
    val title: String,
    val manage: String,
    val add: String,
    val permissions:String,
    val assigned:String,
    val actions: RoleActionsLabels
)