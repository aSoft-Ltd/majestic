package majestic.shared.tools.tabs

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import captain.Navigator
import majestic.tooling.onClick

@Composable
fun Tabs(
    current: String,
    pages: List<TabItem>,
    colors: TabItemColors,
    navigator: Navigator,
    modifier: Modifier = Modifier
) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(4.dp)
) {
    for (page in pages) {
        val interactionSource = remember { MutableInteractionSource() }
        val isHovered by interactionSource.collectIsHoveredAsState()
        val selected = page.isSelected(current)
        Item(
            modifier = Modifier
                .item(selected = selected, isHovered = isHovered, colors = colors)
                .onClick { navigator.navigate(page.path) },
            label = page.label,
            colors = colors,
            selected = selected,
            isHovered = isHovered,
        )
    }
}

data class TabItem(val label: String, val path: String) {
    fun isSelected(url: String): Boolean = path.dropLastSlash().endsWith(url)
    private fun String.dropLastSlash() = if (endsWith("/")) dropLast(1) else this
}
