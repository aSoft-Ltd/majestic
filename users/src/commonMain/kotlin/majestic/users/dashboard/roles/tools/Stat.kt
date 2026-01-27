package majestic.users.dashboard.roles.tools

import org.jetbrains.compose.resources.DrawableResource

data class Stat(
    val icon: DrawableResource,
    val description: String,
    val title: String,
    val value: Number
)