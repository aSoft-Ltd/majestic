@file:OptIn(ExperimentalMaterial3Api::class)

package majestic

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import majestic.popup.Inline
import majestic.popup.Items
import majestic.popup.Overlay

@Composable
fun <T> Popup(
    onDismissRequest: () -> Unit,
    items: Items<T>,
    inline: Inline,
    expanded: Boolean = false,
    anchor: ExposedDropdownMenuAnchorType = ExposedDropdownMenuAnchorType.PrimaryNotEditable,
    modifier: Modifier = Modifier,
) = Popup(
    onDismissRequest = onDismissRequest,
    modifier = modifier,
    expanded = expanded,
    anchor = anchor,
    inline = inline,
    overlay = Overlay(items.modifier, items.shape) {
        for (item in items.data) Box(
            modifier = items.item.modifier.testTag(items.item.tag(item)).clickable(
                interactionSource = NoRippleInteractionSource,
                indication = null,
                enabled = true,
                onClickLabel = null,
                role = Role.ValuePicker,
                onClick = { items.item.onClick(item) }
            )
        ) {
            items.item.content(item)
        }
    }
)

@Composable
fun Popup(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    expanded: Boolean = false,
    anchor: ExposedDropdownMenuAnchorType = ExposedDropdownMenuAnchorType.PrimaryNotEditable,
    inline: Inline,
    overlay: Overlay,
) = ExposedDropdownMenuBox(
    expanded = expanded,
    onExpandedChange = { },
    modifier = modifier
) {
    Box(
        modifier = inline.modifier.menuAnchor(type = anchor).then(inline.tag?.let { Modifier.testTag(it) } ?: Modifier),
        contentAlignment = inline.alignment
    ) {
        inline.content(this, expanded)
    }
    ExposedDropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        modifier = overlay.modifier.clip(overlay.shape),
        shape = overlay.shape,
        containerColor = overlay.background,
        shadowElevation = overlay.shadowElevation,
        tonalElevation = overlay.tonalElevation
    ) {
        overlay.content(this)
    }
}