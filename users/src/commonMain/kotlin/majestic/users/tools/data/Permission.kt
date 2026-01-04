package majestic.users.tools.data

import org.jetbrains.compose.resources.DrawableResource

data class Permission(
    val resource: DrawableResource,
    val title: String,
    val description: String,
    val switch: Pair<String, String>,
    val active: Boolean = false
)

data class Permissions(
    val resource: DrawableResource,
    val title: String,
    val description: String,
    val permissions: List<Permission>
)