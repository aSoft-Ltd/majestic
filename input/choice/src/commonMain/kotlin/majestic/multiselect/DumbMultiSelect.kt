package majestic.multiselect

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import majestic.NoRippleInteractionSource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> DumbMultiSelect(
    items: Collection<T>,
    selectedItems: List<T>,
    onItemClick: (T) -> Unit,
    item: @Composable (T) -> Unit,
    listItem: @Composable (T, () -> Unit) -> Unit,
    placeholder: @Composable () -> Unit = {
        Text("Select", modifier = Modifier.fillMaxWidth())
    },
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable ((Boolean) -> Unit)? = null,
    modifier: Modifier = Modifier,
    multiSelectDefaults: MultiSelectDefaults
) {
    var expanded by remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .clip(shape = multiSelectDefaults.selectedDefaults.containerShape)
                .background(color = multiSelectDefaults.selectedDefaults.containerColor, shape = multiSelectDefaults.selectedDefaults.containerShape)
                .border(
                    width = multiSelectDefaults.selectedDefaults.borderWidth,
                    color = multiSelectDefaults.selectedDefaults.borderColor,
                    shape = multiSelectDefaults.selectedDefaults.borderShape
                )
                .exposedDropdownSize()
                .menuAnchor(type = MenuAnchorType.PrimaryEditable)
                .clickable(
                    interactionSource = NoRippleInteractionSource,
                    indication = null,
                    onClick = { expanded = true }
                )
                .padding(8.dp)
                .menuAnchor(type = MenuAnchorType.PrimaryEditable)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = multiSelectDefaults.selectedDefaults.containerColor,
                        shape = multiSelectDefaults.selectedDefaults.containerShape
                    )
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (leadingIcon != null) {
                    leadingIcon()
                }

                if (selectedItems.isEmpty()) {
                    Box(modifier = Modifier.weight(1f)) { placeholder() }
                } else {
                    Row(
                        modifier = Modifier.weight(1f).horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.Start),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        selectedItems.forEach { selectedItem ->
                            item(selectedItem)
                        }
                    }
                }

                if (trailingIcon != null) {
                    trailingIcon(expanded)
                }

            }
        }

        ExposedDropdownMenu(
            containerColor = multiSelectDefaults.dropDownDefaults.containerColor,
            shape = multiSelectDefaults.dropDownDefaults.shape,
            shadowElevation = multiSelectDefaults.dropDownDefaults.shadowElevation,
            border = multiSelectDefaults.dropDownDefaults.border,
            tonalElevation = multiSelectDefaults.dropDownDefaults.tonalElevation,
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.exposedDropdownSize()
        ) {
            items.forEach { itemValue ->
                listItem(itemValue) { onItemClick(itemValue) }
            }
        }
    }
}