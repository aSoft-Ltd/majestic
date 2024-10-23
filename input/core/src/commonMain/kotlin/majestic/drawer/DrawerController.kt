package majestic.drawer

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.runtime.mutableStateOf

class DrawerController internal constructor(
    state: DrawerState,
    internal val overlap: Boolean,
    internal val animation: AnimationSpec<Float>,
    internal val ratio: Float,
    internal val direction: DrawerOpenDirection
) {
    internal val state = mutableStateOf(state)

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