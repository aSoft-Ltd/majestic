package majestic.shared.tools.filters

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import majestic.dropdown.Dropdown
import majestic.dropdown.DropdownColors
import majestic.shared.tools.dropdown.toDropdownItems
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.vectorResource

data class SingleChoiceBulkAction(
    val icon: DrawableResource,
    val items: List<String>,
    val defaultSelection: String? = null,
    val onSelection: (String) -> Unit = {}
)

@Composable
fun SingleChoiceBulkActions(
    colors: DropdownColors,
    actions: List<SingleChoiceBulkAction>,
    modifier: Modifier = Modifier,
) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.spacedBy(5.dp, alignment = Alignment.End),
    verticalAlignment = Alignment.CenterVertically
) {
    actions.forEach { action ->
        if (action.items.isNotEmpty()) {
            var selected by remember(action.items, action.defaultSelection) {
                mutableStateOf(action.defaultSelection ?: action.items.first())
            }

            Dropdown(
                items = action.items.toDropdownItems(),
                selected = selected,
                onSelection = {
                    selected = it
                    action.onSelection(it)
                },
                intrinsicWidth = true,
                colors = colors,
                leadingIcon = vectorResource(action.icon),
                modifier = Modifier.wrapContentSize(),
            )
        }
    }
}