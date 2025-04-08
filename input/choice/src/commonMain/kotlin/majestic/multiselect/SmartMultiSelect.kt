package majestic.multiselect

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun <T> SmartMultiSelect(
    modifier: Modifier = Modifier,
    items: Collection<T>,
    selectedItems: List<T>,
    onItemToggled: (T) -> Unit,
    item: @Composable (T) -> Unit = { Text("$it") },
    dropDownItem: @Composable (T, () -> Unit) -> Unit = { option, onToggle ->
        Text("$option", modifier = Modifier.clickable { onToggle() })
    },
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable ((Boolean) -> Unit)? = null,
    placeholder: @Composable () -> Unit = {
        Text("Select", modifier = Modifier.fillMaxWidth())
    },
    multiSelectDefaults: MultiSelectDefaults = MultiSelectDefaults(
        selectedDefaults = SelectedDefaults(
            containerColor = Color.Transparent,
            containerShape = RoundedCornerShape(8.dp),
            borderWidth = 0.dp,
            borderColor = Color.Transparent,
            borderShape = RoundedCornerShape(8.dp)
        ),
        dropDownDefaults = DropDownDefaults(
            containerColor = Color.Transparent,
            shape = RoundedCornerShape(8.dp),
            shadowElevation = 0.dp,
            border = BorderStroke(0.dp, Color.Transparent),
            tonalElevation = 0.dp
        )
    )
) {
    DumbMultiSelect(
        items = items,
        selectedItems = selectedItems,
        onItemClick = onItemToggled,
        item = item,
        listItem = dropDownItem,
        placeholder = placeholder,
        modifier = modifier,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        multiSelectDefaults = multiSelectDefaults
    )
}