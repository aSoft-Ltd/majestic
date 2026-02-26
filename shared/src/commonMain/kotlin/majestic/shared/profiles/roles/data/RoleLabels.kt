package majestic.shared.profiles.roles.data

import majestic.shared.profiles.roles.details.header.RolesHeaderLabels
import majestic.shared.profiles.roles.details.roles.RoleActionLabels

data class RoleLabels(
    val header: String,
    val roles: String,
    val options: OptionLabels,
    val action: RoleActionLabels,
    val assignment: RoleAssignmentLabels,
    val details: RolesHeaderLabels
)
