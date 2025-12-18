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
    // Remove leading/trailing slashes for comparison
    val normalizedCurrent = currentUrl.trim('/').split('?')[0] // Remove query params
    val normalizedPath = this.path.trim('/')

    // Check if current URL starts with this path
    return normalizedCurrent == normalizedPath ||
            normalizedCurrent.startsWith("$normalizedPath/")
}