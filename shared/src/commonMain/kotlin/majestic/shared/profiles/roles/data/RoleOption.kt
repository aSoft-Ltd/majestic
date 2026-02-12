package majestic.shared.profiles.roles.data

data class OptionLabels(val view: String, val edit: String)

enum class RoleOption {
    View, Edit;

    fun getRoleLabel(label: OptionLabels) = when (this) {
        View -> label.view
        Edit -> label.edit
    }
}