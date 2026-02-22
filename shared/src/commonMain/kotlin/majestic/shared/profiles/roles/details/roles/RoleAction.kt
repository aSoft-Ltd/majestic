package majestic.shared.profiles.roles.details.roles

data class RoleActionLabels(
    val view: String,
    val unassign: String
)

enum class RoleAction {
    View, Unassign;

    fun getLabels(labels: RoleActionLabels) = when (this) {
        View -> labels.view
        Unassign -> labels.unassign
    }
}