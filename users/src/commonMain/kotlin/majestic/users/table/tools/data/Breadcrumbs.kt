package majestic.users.table.tools.data

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import majestic.ThemeColor

@Composable
internal fun Breadcrumbs(
    url: String,
    theme: ThemeColor,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit = {}
) {
    val breadcrumbs = parseUrl(url)

    Row(modifier = modifier) {
        for ((index, item) in breadcrumbs.withIndex()) {
            Text(
                text = item.label,
                modifier = Modifier
                    .pointerHoverIcon(PointerIcon.Hand)
                    .clickable { onClick(item.url) },
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

data class BreadcrumbItem(val label: String, val url: String)
