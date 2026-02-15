package majestic.shared.profiles.roles.data

import org.jetbrains.compose.resources.DrawableResource

data class RoleData(
    val resource: DrawableResource,
    val station: String,
    val roles: List<Role>
)