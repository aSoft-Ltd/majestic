package majestic.shared.profiles.roles.data

data class OptionLabels(val view: String, val edit: String)

enum class RoleOption {
    ViewRoles, Edit;

    fun getRoleLabel(label: OptionLabels) = when (this) {
        ViewRoles -> label.view
        Edit -> label.edit
    }
}