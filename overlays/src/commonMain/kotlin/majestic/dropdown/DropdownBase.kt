package majestic.dropdown

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import majestic.Popup
import majestic.button.Button
import majestic.button.appearence.dropdownTrigger
import majestic.button.appearence.listItemIconButton
import majestic.popup.Inline
import majestic.popup.Overlay

// dropdown popup implementation
@Composable
internal fun <T> DropdownBase(
    items: List<DropdownItem<T>>,
    mode: DropdownMode,
    colors: DropdownColors,
    onItemClick: (T) -> Unit,
    enabled: Boolean,
    isListItem: Boolean = false,
    loading: Boolean = false,
    popupWidth: Dp?,
    matchButtonWidth: Boolean,
    modifier: Modifier = Modifier,
    triggerContent: @Composable (expanded: Boolean) -> Unit,
    itemContent: @Composable (DropdownItem<T>) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    var buttonWidthPx by remember { mutableStateOf(0) }
    val density = LocalDensity.current
    val buttonWidthDp = remember(buttonWidthPx) { with(density) { buttonWidthPx.toDp() } }

    Popup(
        expanded = expanded,
        onDismissRequest = { expanded = false },
        inline = Inline {
            Button(
                modifier = modifier
                    .let {
                        if (isListItem) {
                            it.listItemIconButton(
                                color = colors.trigger,
                                onClick = { if (enabled && !loading) expanded = !expanded })
                        }
                        else {
                            it.dropdownTrigger(
                                color = colors.trigger,
                                onClick = { if (enabled && !loading) expanded = !expanded })
                        }
                    }
                    .onSizeChanged { buttonWidthPx = it.width },
            ) {
                triggerContent(expanded)
            }
        },
        overlay = Overlay(
            shape = RoundedCornerShape(14.dp),
            shadowElevation = 16.dp,
            tonalElevation = 16.dp,
            modifier = when {
                popupWidth != null -> Modifier.width(popupWidth)
                matchButtonWidth -> Modifier.width(buttonWidthDp)
                else -> Modifier.width(IntrinsicSize.Max)
            }
        ) {

            Box(
                modifier = Modifier
                    .then(
                        when {
                            popupWidth != null -> Modifier.width(popupWidth)
                            matchButtonWidth -> Modifier.width(buttonWidthDp)
                            else -> Modifier.width(IntrinsicSize.Max)
                        }
                    )
                    .heightIn(max = 300.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(colors.dropdown)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .verticalScroll(scrollState)
                ) {
                    items.forEach { item ->
                        DropdownItemWrapper(
                            item = item,
                            mode = mode,
                            color = colors.trigger,
                            onClick = {
                                onItemClick(item.value)
                                if (mode != DropdownMode.Multi) expanded = false
                            }
                        ) {
                            itemContent(item)
                        }
                    }
                }
            }
        }
    )
}