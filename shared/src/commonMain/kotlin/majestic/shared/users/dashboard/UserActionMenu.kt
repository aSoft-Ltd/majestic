package majestic.shared.users.dashboard

enum class UserActionMenu {
    AssignRole,
    ManagePermission,
    ViewUser,
    BlockAccess,
    ResetPassword,
    RemoveUser;

    fun getLabel(labels: UserActionLabels) = when (this) {
        AssignRole -> labels.assignRole
        ManagePermission -> labels.managePermission
        ViewUser -> labels.viewUser
        BlockAccess -> labels.blockAccess
        ResetPassword -> labels.resetPassword
        RemoveUser -> labels.removeUser
    }
}

data class UserActionLabels(
    val assignRole: String,
    val managePermission: String,
    val viewUser: String,
    val blockAccess: String,
    val resetPassword: String,
    val removeUser: String,
)