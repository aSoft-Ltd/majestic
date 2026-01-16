package majestic.users.labels

data class SummaryCardLabels(
    val totalUsers: String,
    val totalRoles: String,
    val activeUsers: String,
    val totalPermissions: String,
    val pendingInvites: String
) {
    companion object {
        val english by lazy {
            SummaryCardLabels(
                "Total Users",
                "Total Roles",
                "Active Users",
                "Total Permissions",
                "Pending Invites"
            )
        }
        val swahili by lazy {
            SummaryCardLabels(
                "Watumiaji Wote",
                "Majukumu Yote",
                "Watumiaji Hai",
                "Ruhusa Zote",
                "Mihariko Isiojibiwa"
            )
        }
    }
}