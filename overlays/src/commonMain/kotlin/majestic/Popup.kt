@file:OptIn(ExperimentalMaterial3Api::class)

package majestic

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.MenuAnchorType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import majestic.popup.Inline
import majestic.popup.Items
import majestic.popup.Overlay
import kotlin.time.Duration.Companion.milliseconds

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

@Composable
fun Popup(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    expanded: Boolean = false,
    anchor: MenuAnchorType = MenuAnchorType.PrimaryEditable,
    inline: Inline,
    overlay: Overlay,
) {
    val scope = rememberCoroutineScope()
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
            onDismissRequest = {
                scope.launch {
                    if (!expanded) return@launch
                    // Debouncing the onDismissRequest so that the popup doesn't close and open
                    delay(500.milliseconds)
                    onDismissRequest()
                }
            },
            modifier = overlay.modifier.clip(overlay.shape),
            shape = overlay.shape,
            containerColor = overlay.background
        ) {
            overlay.content(this)
        }
    }
}