package majestic.shared.appbar

import org.jetbrains.compose.resources.DrawableResource

class Action<T>(
    val action: T,
    val label: String,
    val icon: DrawableResource?,
    val handler: (T) -> Unit,
)
