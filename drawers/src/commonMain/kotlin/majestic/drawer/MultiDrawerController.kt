package majestic.drawer

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import kotlin.properties.ReadOnlyProperty

/**
 * Controls a [DrawerHost] that can contain multiple drawers.
 *
 * Methods that accept a [Drawer] target one concrete drawer instance. Methods
 * that accept [Any] treat the value as a key and affect every drawer registered
 * with that key. Parameterless operations come from [SingleDrawerController] and
 * affect every drawer known to the controller.
 */
interface MultiDrawerController : SingleDrawerController {
    /** Opens [drawer] using its configured size. */
    fun open(drawer: Drawer)
    /** Opens all drawers registered with [drawer] as their key. */
    fun open(drawer: Any)

    /** Opens every supplied drawer using each drawer's configured size. */
    fun open(vararg drawers: Drawer)

    /** Opens [drawer] using [ratio] as a temporary size override. */
    fun open(drawer: Drawer, ratio: Float)
    /** Opens all drawers registered with [drawer] as their key using [ratio]. */
    fun open(drawer: Any, ratio: Float)

    /** Opens [drawer] using [span] as a temporary size override. */
    fun open(drawer: Drawer, span: Dp)
    /** Opens all drawers registered with [drawer] as their key using [span]. */
    fun open(drawer: Any, span: Dp)


    /** Closes [drawer]. */
    fun close(drawer: Drawer)
    /** Closes all drawers registered with [drawer] as their key. */
    fun close(drawer: Any)


    /** Toggles [drawer] using its configured size when opening. */
    fun toggle(drawer: Drawer)
    /** Toggles all drawers registered with [drawer] as their key. */
    fun toggle(drawer: Any)

    /** Toggles [drawer] using [ratio] as a temporary size override when opening. */
    fun toggle(drawer: Drawer, ratio: Float)
    /** Toggles all drawers registered with [drawer] as their key using [ratio] when opening. */
    fun toggle(drawer: Any, ratio: Float)

    /** Toggles [drawer] using [span] as a temporary size override when opening. */
    fun toggle(drawer: Drawer, span: Dp)
    /** Toggles all drawers registered with [drawer] as their key using [span] when opening. */
    fun toggle(drawer: Any, span: Dp)

    /**
     * Registers a drawer under [drawer] and returns the created [Drawer].
     *
     * Runtime-added drawers default to [DrawerPosition.Right] and
     * [DrawerDisplay.Overlay].
     */
    fun add(
        drawer: Any,
        ratio: Float,
        position: DrawerPosition = DrawerPosition.Right,
        display: DrawerDisplay = DrawerDisplay.Overlay,
        backdrop: Color = Color.Transparent,
        content: @Composable BoxScope.(DrawerContext) -> Unit,
    ): Drawer

    /**
     * Creates a property delegate that registers a ratio-sized drawer using the
     * delegated property name as its key.
     *
     * Runtime-added drawers default to [DrawerPosition.Right] and
     * [DrawerDisplay.Overlay].
     */
    fun add(
        ratio: Float,
        position: DrawerPosition = DrawerPosition.Right,
        display: DrawerDisplay = DrawerDisplay.Overlay,
        backdrop: Color = Color.Transparent,
        content: @Composable BoxScope.(DrawerContext) -> Unit,
    ): ReadOnlyProperty<Any?, Drawer>

    /**
     * Registers a fixed-size drawer under [key] and returns the created [Drawer].
     *
     * Runtime-added drawers default to [DrawerPosition.Right] and
     * [DrawerDisplay.Overlay].
     */
    fun add(
        key: Any,
        span: Dp,
        position: DrawerPosition = DrawerPosition.Right,
        display: DrawerDisplay = DrawerDisplay.Overlay,
        backdrop: Color = Color.Transparent,
        content: @Composable BoxScope.(DrawerContext) -> Unit,
    ): Drawer

    /**
     * Creates a property delegate that registers a fixed-size drawer using the
     * delegated property name as its key.
     *
     * Runtime-added drawers default to [DrawerPosition.Right] and
     * [DrawerDisplay.Overlay].
     */
    fun add(
        span: Dp,
        position: DrawerPosition = DrawerPosition.Right,
        display: DrawerDisplay = DrawerDisplay.Overlay,
        backdrop: Color = Color.Transparent,
        content: @Composable BoxScope.(DrawerContext) -> Unit,
    ): ReadOnlyProperty<Any?, Drawer>

    /** Returns the current state of [drawer]. Unknown drawers are treated as [DrawerState.Closed]. */
    fun state(drawer: Drawer): DrawerState
    /** Returns the current state for [drawer], or [DrawerState.Closed] when it is unknown. */
    fun state(drawer: Any): DrawerState

    /** Removes [drawer] from the host and returns it when it was registered. */
    fun remove(drawer: Drawer?): Drawer?
    /** Removes all drawers registered with [key] and returns the removed drawers. */
    fun remove(key: Any): List<Drawer>
}
