package users.data

import org.jetbrains.compose.resources.DrawableResource

data class Role(
    val resource: DrawableResource,
    val title: String,
    val description: String,
    val roles: List<String>
)