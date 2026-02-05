package majestic.shared.profiles

import org.jetbrains.compose.resources.DrawableResource

data class Action(
    val resource: DrawableResource,
    val title: String,
    val description: String,
    val switch: Pair<String, String>,
    val active: Boolean = false
)

data class Permission(
    val resource: DrawableResource,
    val title: String,
    val description: String,
    val actions: List<Action>
)