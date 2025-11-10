package majestic.users.tools.data

import androidx.compose.ui.graphics.Color

data class UsersStatusLabels(
    val invited: String,
    val active: String,
    val declined: String,
    val revoked: String
)

enum class UserStatus {
    Invited,
    Active,
    Declined,
    Revoked;

    fun getLabels(labels: UsersStatusLabels) = when (this) {
        Invited -> labels.invited
        Active -> labels.active
        Declined -> labels.declined
        Revoked -> labels.revoked
    }

    fun getColors() = when (this) {
        Invited -> Color(0xFF4FC3F7)
        Active -> Color(0xFF81C784)
        Declined -> Color(0xFF9575CD)
        Revoked -> Color(0xFFE15B5B)
    }
}