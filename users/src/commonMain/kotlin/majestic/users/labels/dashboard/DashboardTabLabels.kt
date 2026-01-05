package majestic.users.labels.dashboard

data class DashboardTabLabels(
    val users: String,
    val roles: String
) {
    companion object {
        val english by lazy {
            DashboardTabLabels(
                users = "Users",
                roles = "Roles"
            )
        }
        val swahili by lazy {
            DashboardTabLabels(
                users = "Watumiaji",
                roles = "Majukumu"
            )
        }
    }
}
