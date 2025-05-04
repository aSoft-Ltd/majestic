package majestic.drawer

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.runtime.mutableStateOf

class DrawerController internal constructor(
    state: DrawerState,
    overlap: Boolean,
    val animation: AnimationSpec<Float>,
    ratio: Float,
    direction: DrawerOpenDirection
) {
    internal val state = mutableStateOf(state)
    internal val overlap = mutableStateOf(overlap)
    internal val ratio = mutableStateOf(ratio)
    internal val direction = mutableStateOf(direction)

    val isOpen get() = state.value == DrawerState.Open
    val isClosed get() = state.value == DrawerState.Closed

    fun open() {
        state.value = DrawerState.Open
    }

    fun close() {
        state.value = DrawerState.Closed
    }

    fun toggle() {
        state.value = state.value.toggled()
    }
}