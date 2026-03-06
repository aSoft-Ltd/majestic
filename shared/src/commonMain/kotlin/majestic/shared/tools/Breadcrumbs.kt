package majestic.shared.tools

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.style.TextOverflow
import majestic.ThemeColor
import majestic.tooling.onClick

private data class BreadcrumbItem(val label: String, val url: String)

@Deprecated(
    message = "Use Breadcrumb with items instead",
    replaceWith = ReplaceWith("Breadcrumb(color, items, onClick, modifier)", "majestic.shared.tools.breadcrumbs.Breadcrumb")
)
@Composable
fun Breadcrumbs(
    url: String,
    theme: ThemeColor,
    onClick: (String) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val breadcrumbs = parseUrl(url)

    Row(modifier = modifier) {
        for ((index, item) in breadcrumbs.withIndex()) {
            Text(
                text = item.label,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .pointerHoverIcon(PointerIcon.Hand)
                    .onClick { onClick(item.url) },
                color = if (index == breadcrumbs.lastIndex) {
                    theme.surface.contra.color
                } else {
                    theme.surface.contra.color.copy(alpha = 0.5f)
                }
            )

            if (index < breadcrumbs.lastIndex) {
                Text(
                    text = " / ",
                    color = if (index == breadcrumbs.lastIndex - 1) {
                        theme.surface.contra.color
                    } else {
                        theme.surface.contra.color.copy(alpha = 0.5f)
                    }
                )
            }
        }
    }
}

private fun parseUrl(url: String): List<BreadcrumbItem> {
    val segments = url.split("/").filter { it.isNotEmpty() }
    val breadcrumbs = mutableListOf<BreadcrumbItem>()
    var currentUrl = ""

    for (segment in segments) {
        val label = segment.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
        currentUrl += "/$segment"
        breadcrumbs.add(BreadcrumbItem(label, currentUrl))
    }

    return breadcrumbs
}
