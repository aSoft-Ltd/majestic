package users

enum class UserActionMenu {
    AssignRole,
    ManagePermission,
    ViewUser,
    BlockAccess,
    RemoveUser,
    ResetPassword;

    fun getLabel(labels: UserActionLabels) = when (this) {
        AssignRole -> labels.assignRole
        ManagePermission -> labels.managePermission
        ViewUser -> labels.viewUser
        BlockAccess -> labels.blockAccess
        RemoveUser -> labels.removeUser
        ResetPassword -> labels.resetPassword
    }
}

data class UserActionLabels(
    val assignRole: String,
    val managePermission: String,
    val viewUser: String,
    val blockAccess: String,
    val removeUser: String,
    val resetPassword: String
)