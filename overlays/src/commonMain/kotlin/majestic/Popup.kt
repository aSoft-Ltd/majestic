@file:OptIn(ExperimentalMaterial3Api::class)

package majestic

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import majestic.popup.Inline
import majestic.popup.Items
import majestic.popup.Overlay

@Deprecated("In favour of Popup")
@Composable
fun <T> PopupMenu(
    items: Collection<T>,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    dropdownModifier: Modifier = Modifier.width(200.dp),
    shape: Shape = MenuDefaults.shape,
    expanded: Boolean = false,
    onClick: ((T) -> Unit)? = null,
    item: @Composable (T) -> Unit = { Text("$it") },
    icon: @Composable () -> Unit,
) {
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {},
        modifier = modifier
    ) {
        Box(modifier = Modifier.menuAnchor(type = MenuAnchorType.PrimaryEditable)) {
            icon()
        }
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = onDismissRequest,
            modifier = dropdownModifier,
            shape = shape
        ) {
            for (it in items) DropdownMenuItem(
                modifier = Modifier.fillMaxWidth(),
                text = { item(it) },
                onClick = { onClick?.invoke(it) },
            )
        }
    }
}


@Composable
fun <T> Popup(
    onDismissRequest: () -> Unit,
    items: Items<T>,
    inline: Inline,
    expanded: Boolean = false,
    anchor: MenuAnchorType = MenuAnchorType.PrimaryEditable,
    modifier: Modifier = Modifier,
) = Popup(
    onDismissRequest = onDismissRequest,
    modifier = modifier,
    expanded = expanded,
    anchor = anchor,
    inline = inline,
    overlay = Overlay(items.modifier, items.shape) {
        for (item in items.data) Box(modifier = items.item.modifier) {
            items.item.content(item)
        }
    }
)

@Deprecated("In favour of Popup(overlay = Overlay)")
@Composable
fun PopupMenu(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    dropdownModifier: Modifier = Modifier.width(200.dp),
    shape: Shape = MenuDefaults.shape,
    containerColor: Color = MenuDefaults.containerColor,
    expanded: Boolean = false,
    icon: @Composable () -> Unit,
    content: @Composable () -> Unit,
) {
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {},
        modifier = modifier
    ) {
        Box(modifier = Modifier.menuAnchor(type = MenuAnchorType.PrimaryEditable)) {
            icon()
        }
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = onDismissRequest,
            modifier = dropdownModifier,
            shape = shape,
            containerColor = containerColor
        ) {
            content()
        }
    }
}

@Composable
fun Popup(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    expanded: Boolean = false,
    anchor: MenuAnchorType = MenuAnchorType.PrimaryEditable,
    inline: Inline,
    overlay: Overlay,
) {
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {},
        modifier = modifier
    ) {
        Box(modifier = inline.modifier.menuAnchor(type = anchor), contentAlignment = inline.alignment) {
            inline.content(this, expanded)
        }
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = onDismissRequest,
            modifier = overlay.modifier,
            shape = overlay.shape,
            containerColor = Color.Unspecified
        ) {
            overlay.content(this)
        }
    }
}