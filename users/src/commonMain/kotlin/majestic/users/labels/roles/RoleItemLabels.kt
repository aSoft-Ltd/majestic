package majestic.users.labels.roles

data class RoleItemLabels(
    val setupBadge: String,
    val setupAction: String,
    val permissions: String,
    val assignAction: String,
    val unassignAction: String
) {
    companion object {
        val english by lazy {
            RoleItemLabels(
                setupBadge = "Setup Required",
                setupAction = "Setup",
                permissions = "Permissions",
                assignAction = "Assign",
                unassignAction = "Unassign"
            )
        }

        val swahili by lazy {
            RoleItemLabels(
                setupBadge = "Inahitaji Usanidi",
                setupAction = "Sanidi",
                permissions = "Ruhusa",
                assignAction = "Teua",
                unassignAction = "Ondoa"
            )
        }
    }
}