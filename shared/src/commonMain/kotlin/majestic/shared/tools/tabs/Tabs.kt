package majestic.shared.tools.tabs

import androidx.compose.foundation.hoverable
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

data class TabItem(val label: String, val path: String) {
    fun isSelected(url: String): Boolean = path.dropLastSlash().endsWith(url)
    private fun String.dropLastSlash() = if (endsWith("/")) dropLast(1) else this
}


@Composable
fun Tabs(
    current: String,
    pages: List<TabItem>,
    colors: TabItemColors,
    navigator: Navigator,
    modifier: Modifier = Modifier
) = Tabs(
    items = pages,
    selected = pages.firstOrNull { it.isSelected(current) },
    onSelect = { navigator.navigate(it?.path ?: "") },
    label = { it?.label ?: "" },
    colors = colors,
    modifier = modifier,
)

@Composable
fun <T> Tabs(
    items: List<T>,
    selected: T,
    onSelect: (T) -> Unit,
    label: (T) -> String,
    colors: TabItemColors,
    modifier: Modifier = Modifier,
) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(4.dp)
) {
    for (item in items) {
        val interactionSource = remember { MutableInteractionSource() }
        val isHovered by interactionSource.collectIsHoveredAsState()
        val isSelected = item == selected
        Item(
            modifier = Modifier
                .hoverable(interactionSource)
                .item(selected = isSelected, isHovered = isHovered, colors = colors)
                .onClick { onSelect(item) },
            label = label(item),
            colors = colors,
            selected = isSelected,
            isHovered = isHovered,
        )
    }
}