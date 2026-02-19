package majestic.shared.profiles.roles.data

data class RoleLabels(
    val header: String,
    val roles: String,
    val options: OptionLabels,
    val assignment: RoleAssignmentLabels
)