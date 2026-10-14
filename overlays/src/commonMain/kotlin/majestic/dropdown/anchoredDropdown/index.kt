package majestic.dropdown.anchoredDropdown

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import majestic.dropdown.DropdownColors
import majestic.dropdown.DropdownItem
import majestic.dropdown.DropdownItemRow
import majestic.dropdown.DropdownItemWrapper
import majestic.dropdown.DropdownMode
import majestic.dropdown.overlayModifier

enum class DropdownAlignment { Auto, Start, Center, End }

@Composable
fun <T> AnchoredDropdown(
    onAction: (T) -> Unit,
    items: List<DropdownItem<T>>,
    colors: DropdownColors,
    enabled: Boolean = true,
    loading: Boolean = false,
    popupWidth: Dp? = null,
    popupAlignment: DropdownAlignment = DropdownAlignment.Auto,
    popupGap: Dp = 8.dp,
    modifier: Modifier = Modifier,
    customItemRow: @Composable ((DropdownItem<T>) -> Unit)? = null,
    customTrigger: @Composable (expanded: Boolean, onToggle: () -> Unit) -> Unit
) = Box(modifier = modifier) {
    val visibility = remember { MutableTransitionState(false) }
    val shadowPadding = 16.dp
    val density = LocalDensity.current
    val position = remember(popupAlignment, popupGap, density) {
        with(density) {
            DropdownPositionProvider(popupAlignment, popupGap.roundToPx(), shadowPadding.roundToPx())
        }
    }

    customTrigger(visibility.targetState) {
        if (enabled && !loading) visibility.targetState = !visibility.targetState
    }
    LaunchedEffect(enabled, loading) {
        if (!enabled || loading) visibility.targetState = false
    }

    if (visibility.currentState || visibility.targetState) {
        Popup(
            popupPositionProvider = position,
            onDismissRequest = { visibility.targetState = false },
            properties = PopupProperties(focusable = true)
        ) {
            val focus = LocalFocusManager.current
            AnimatedVisibility(
                visibleState = visibility,
                enter = fadeIn(tween(150)),
                exit = fadeOut(tween(150))
            ) {
                val shape = RoundedCornerShape(14.dp)
                Box(modifier = Modifier.padding(shadowPadding)) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(2.dp),
                        modifier = Modifier
                            .overlayModifier(popupWidth = popupWidth)
                            .shadow(
                                elevation = 16.dp,
                                shape = shape
                            )
                            .background(
                                color = colors.dropdown,
                                shape = shape
                            )
                            .clip(shape)
                            .onPreviewKeyEvent {
                                when {
                                    it.type != KeyEventType.KeyDown -> false
                                    it.key == Key.DirectionDown -> focus.moveFocus(FocusDirection.Next)
                                    it.key == Key.DirectionUp -> focus.moveFocus(FocusDirection.Previous)
                                    else -> false
                                }
                            }
                            .verticalScroll(rememberScrollState())
                            .padding(8.dp)
                    ) {
                        LaunchedEffect(Unit) { focus.moveFocus(FocusDirection.Next) }
                        items.forEach { item ->
                            DropdownItemWrapper(
                                item = item,
                                mode = DropdownMode.Action,
                                color = colors,
                                onClick = {
                                    visibility.targetState = false
                                    onAction(item.value)
                                }
                            ) {
                                if (customItemRow != null)
                                    customItemRow(item)
                                else
                                    DropdownItemRow(
                                        item = item,
                                        colors = colors,
                                        isSelected = false,
                                        isDestructive = item.isDestructive
                                    )
                            }
                        }
                    }
                }
            }
        }
    }
}