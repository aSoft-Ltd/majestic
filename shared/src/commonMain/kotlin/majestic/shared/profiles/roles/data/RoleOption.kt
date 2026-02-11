package majestic.shared.profiles.roles.data

enum class RoleOption {
    ViewRoles;

    fun getRoleLabel(label: String = "View Roles") = when (this) {
        ViewRoles -> label
    }
}