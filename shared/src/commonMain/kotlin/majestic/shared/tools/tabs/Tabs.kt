package majestic.shared.tools.tabs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import captain.Navigator
import majestic.ThemeColor

@Composable
fun Tabs(
    current: String,
    pages: List<TabItem>,
    theme: ThemeColor,
    navigator: Navigator,
    modifier: Modifier = Modifier
) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(4.dp)
) {
    for (page in pages) Item(
        label = page.label,
        theme = theme,
        selected = page.isSelected(current),
        onClick = { navigator.navigate(page.path) }
    )
}

data class TabItem(val label: String, val path: String) {
    fun isSelected(url: String): Boolean = path.dropLastSlash().endsWith(url)
    private fun String.dropLastSlash() = if (endsWith("/")) dropLast(1) else this
}
