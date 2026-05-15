package majestic.shared.menu

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import majestic.Popup
import majestic.popup.Inline
import majestic.popup.Overlay

@Composable
fun PanelMenu(
    modifier: Modifier = Modifier,
    trigger: @Composable BoxScope.(expanded: Boolean, onToggle: () -> Unit) -> Unit,
    content: @Composable ColumnScope.(onClose: () -> Unit) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }

    Popup(
        expanded = expanded,
        onDismissRequest = { expanded = false },
        inline = Inline {
            trigger(expanded) { expanded = !expanded }
        },
        overlay = Overlay(
            shape = RoundedCornerShape(11.dp),
            shadowElevation = 11.dp,
            tonalElevation = 11.dp,
            modifier = Modifier.offset(y = 3.dp).width(IntrinsicSize.Max)
        ) {
            Column(modifier = modifier) {
                content { expanded = false }
            }
        }
    )
}