package majestic.multiselect

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import majestic.multiselect.defaults.MultiSelectDefaults

@Composable
fun <T> SmartMultiSelect(
    modifier: Modifier = Modifier,
    items: Collection<T>,
    selectedItems: List<T>,
    onItemToggled: (T) -> Unit,
    selectedItem: @Composable (T) -> Unit = { Text("$it") },
    item: @Composable (T, () -> Unit) -> Unit = { option, onToggle ->
        Text("$option", modifier = Modifier.clickable { onToggle() })
    },
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable ((Boolean) -> Unit)? = null,
    placeholder: @Composable () -> Unit = {
        Text("Select", modifier = Modifier.fillMaxWidth())
    },
    multiSelectDefaults: MultiSelectDefaults = MultiSelectDefaults.Default
) {
    DumbMultiSelect(
        items = items,
        selectedItems = selectedItems,
        onItemClick = onItemToggled,
        selectedItem = selectedItem,
        item = item,
        placeholder = placeholder,
        modifier = modifier,
        leadingIcon = { leadingIcon },
        trailingIcon = trailingIcon,
        multiSelectDefaults = multiSelectDefaults
    )
}