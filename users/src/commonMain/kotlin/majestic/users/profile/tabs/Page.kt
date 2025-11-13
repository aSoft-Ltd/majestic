package majestic.users.profile.tabs

import org.jetbrains.compose.resources.DrawableResource


data class Page(
    val label: String,
    val path: String,
    val icon: DrawableResource? = null
)

fun String.toPage(
    icon: DrawableResource? = null,
    endpoint: String
) = Page(this, endpoint, icon)

fun Page.isSelected(currentUrl: String): Boolean {
    return this.path == currentUrl
}