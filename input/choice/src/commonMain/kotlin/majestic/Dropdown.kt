package majestic

import androidx.compose.material3.MenuAnchorType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import cinematic.watchAsState
import majestic.popup.Inline
import majestic.popup.Item
import majestic.popup.Items
import majestic.tooling.onClick
import symphony.SingleChoiceField

@Composable
fun <T> Dropdown(
    field: SingleChoiceField<T>,
    items: Items<T>,
    selected: Selected<T>,
    anchor: MenuAnchorType = MenuAnchorType.PrimaryEditable,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }
    val state = field.state.watchAsState()
    Popup(
        expanded = expanded,
        modifier = modifier,
        items = Items(
            data = state.items,
            modifier = items.modifier,
            item = Item(
                modifier = items.item.modifier,
                content = items.item.content,
                onClick = {
                    if (state.selected?.item == it) {
                        field.unselect()
                    } else {
                        field.select(it)
                    }
                    expanded = false
                }
            )
        ),
        inline = Inline(
            modifier = selected.modifier.onClick { expanded = !expanded },
            alignment = selected.alignment
        ) {
            selected.content(this, Selected.SelectedItem(it, state.selected?.item))
        },
        onDismissRequest = { expanded = false },
        anchor = anchor
    )
}