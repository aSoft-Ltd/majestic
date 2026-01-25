package majestic.shared.users.label.dashboard

data class SummaryCardLabels(
    val totalUsers: String,
    val totalRoles: String,
    val activeUsers: String,
    val totalPermissions: String,
    val pendingInvites: String
)