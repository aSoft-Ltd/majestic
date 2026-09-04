package majestic.dropdown

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
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
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import majestic.Popup
import majestic.popup.Inline
import majestic.popup.Overlay

@Composable
fun FormDropdown(
    onDismissRequest: () -> Unit = {},
    popupBackground: Color,
    enabled: Boolean = true,
    inlinePadding: PaddingValues = PaddingValues(vertical = 8.dp),
    contentPadding: PaddingValues = PaddingValues(horizontal = 8.dp),
    popupMaxHeight: Dp = 300.dp,
    popupShape: Shape = RoundedCornerShape(10.dp),
    shadowElevation: Dp = 16.dp,
    tonalElevation: Dp = 0.dp,
    modifier: Modifier = Modifier,
    trigger: @Composable (expanded: Boolean, onToggle: () -> Unit) -> Unit,
    content: @Composable ColumnScope.(dismiss: () -> Unit) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var popupWidth by remember { mutableStateOf(0.dp) }
    val density = LocalDensity.current
    val dismiss = {
        expanded = false
        onDismissRequest()
    }

    Popup(
        onDismissRequest = dismiss,
        expanded = expanded,
        modifier = modifier,
        inline = Inline(
            modifier = Modifier
                .fillMaxWidth()
                .padding(inlinePadding)
                .onSizeChanged { popupWidth = with(density) { it.width.toDp() } }
        ) {
            trigger(expanded) {
                if (enabled) {
                    if (expanded)
                        dismiss()
                    else
                        expanded = true
                }
            }
        },
        overlay = Overlay(
            shape = popupShape,
            background = popupBackground,
            shadowElevation = shadowElevation,
            tonalElevation = tonalElevation,
            modifier = Modifier.width(popupWidth)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = popupMaxHeight)
                    .clip(popupShape)
                    .background(popupBackground)
                    .padding(contentPadding)
            ) {
                content(dismiss)
            }
        }
    )
}