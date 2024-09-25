@file:OptIn(ExperimentalMaterial3Api::class)

package majestic

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun <T> UncontrolledSelect(
    items: Collection<T>,
    item: @Composable (T) -> Unit,
    selected: @Composable (T) -> Unit = item,
    value: T? = null,
    placeholder: @Composable () -> Unit = {
        Text("Select", modifier = Modifier.fillMaxWidth())
    },
    onClick: ((T) -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    var candidate by remember(value) { mutableStateOf(value) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = false },
        modifier = modifier
    ) {
        Box(modifier = Modifier
            .exposedDropdownSize()
            .menuAnchor(type = MenuAnchorType.PrimaryEditable)
            .clickable { expanded = !expanded }
        ) {
            when (val c = candidate) {
                null -> placeholder()
                else -> selected(c)
            }
        }
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.exposedDropdownSize()
        ) {
            for (it in items) DropdownMenuItem(
                modifier = Modifier.fillMaxWidth(),
                text = { item(it) },
                onClick = {
                    candidate = if(candidate==it) null else it
                    expanded = false
                    onClick?.invoke(it)
                },
            )
        }
    }
}