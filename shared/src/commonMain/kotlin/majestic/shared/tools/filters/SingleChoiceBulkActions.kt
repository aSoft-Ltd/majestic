package majestic.shared.tools.filters

import org.jetbrains.compose.resources.DrawableResource

data class SingleChoiceBulkAction(
    val icon: DrawableResource,
    val items: List<String>,
    val defaultSelection: String? = null,
    val onSelection: (String) -> Unit = {}
)