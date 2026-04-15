package majestic.shared.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import majestic.Popup
import majestic.popup.Inline
import majestic.popup.Overlay

fun Modifier.panelMenu(background: Color, width: Dp? = null) = this
    .then(if (width != null) Modifier.width(width) else Modifier)
    .clip(RoundedCornerShape(11.dp))
    .background(background)
    .padding(6.dp)

@Composable
fun PanelMenu(
    modifier: Modifier = Modifier,
    trigger: @Composable (expanded: Boolean, onToggle: () -> Unit) -> Unit,
    content: @Composable (onClose: () -> Unit) -> Unit,
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
            modifier = Modifier.offset(y = 2.dp).width(IntrinsicSize.Max)
        ) {
            Column(modifier = modifier) {
                content { expanded = false }
            }
        }
    )
}