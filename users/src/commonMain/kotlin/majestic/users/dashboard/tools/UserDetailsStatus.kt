package majestic.users.dashboard.tools

import androidx.compose.ui.graphics.Color

data class UserDetailsStatusLabels(
    val totalUsers: String,
    val totalRoles: String,
    val activeUsers: String,
    val totalPermissions: String,
    val pendingInvites: String
)

enum class UserDetailsStatus(val label: String, val color: Color) {
    USERS("Total Users", Color(0xFF81C784)),
    ROLES("Total Roles", Color(0xFF4FC3F7)),
    ACTIVE("Active Users", Color(0xFF4FC3F7)),
    PERMISSIONS("Total Permissions", Color(0xFFE15B5B)),
    PENDING("Pending Invites", Color(0xFFE15B5B));

    fun getLabel(labels: UserDetailsStatusLabels): String {
        return when (this) {
            USERS -> labels.totalUsers
            ROLES -> labels.totalRoles
            ACTIVE -> labels.activeUsers
            PERMISSIONS -> labels.totalPermissions
            PENDING -> labels.pendingInvites
        }
    }
}
