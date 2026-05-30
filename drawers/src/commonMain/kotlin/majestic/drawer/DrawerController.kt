package majestic.drawer

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.runtime.mutableStateOf

/**
 * Controller used by the deprecated [NavigationDrawer] API.
 *
 * New code should prefer [SingleDrawerController] with [InlineDrawerHost] or
 * [OverlayDrawerHost].
 */
@Deprecated(
    message = "The NavigationDrawer API is deprecated. Use SingleDrawerController with InlineDrawerHost or OverlayDrawerHost instead.",
    replaceWith = ReplaceWith(
        imports = arrayOf(
            "majestic.drawer.SingleDrawerController"
        ),
        expression = "SingleDrawerController",
    )
)
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

    /** `true` when the legacy drawer is currently open. */
    val isOpen get() = state.value == DrawerState.Open

    /** `true` when the legacy drawer is currently closed. */
    val isClosed get() = state.value == DrawerState.Closed

    /** Opens the legacy drawer. */
    fun open() {
        state.value = DrawerState.Open
    }

    /** Closes the legacy drawer. */
    fun close() {
        state.value = DrawerState.Closed
    }

    /** Toggles the legacy drawer between open and closed. */
    fun toggle() {
        state.value = state.value.toggled()
    }
}
