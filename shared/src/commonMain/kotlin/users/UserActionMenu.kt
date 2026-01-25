package users

import menu.OptionMenu

enum class UserActionMenu {
    AssignRole,
    ManagePermission,
    ViewUser,
    BlockAccess,
    RemoveUser,
    ResetPassword;

    fun getLabel(labels: UserLabels) = when (this) {
        AssignRole -> labels.actions.assignRole
        ManagePermission -> labels.actions.managePermission
        ViewUser -> labels.actions.viewUser
        BlockAccess -> labels.actions.blockAccess
        RemoveUser -> labels.actions.removeUser
        ResetPassword -> labels.actions.resetPassword
    }

    companion object {
        fun userActions(labels: UserLabels) =
            entries.map { OptionMenu(it.getLabel(labels), it) }
    }
}

data class UserLabels(
    val actions: UserActionLabels
)

data class UserActionLabels(
    val assignRole: String,
    val managePermission: String,
    val viewUser: String,
    val blockAccess: String,
    val removeUser: String,
    val resetPassword: String
)