package majestic.drawer

import androidx.compose.ui.unit.Dp

/**
 * Controls all drawers owned by a single-drawer host.
 *
 * Use this with [InlineDrawerHost] or [OverlayDrawerHost] when a screen only
 * needs one drawer. Calling the parameterless operations affects the hosted
 * drawer. Passing a ratio or span opens or toggles the drawer with an
 * override size for that state change.
 */
interface SingleDrawerController {
    /** Opens the hosted drawer using its configured size. */
    fun open()
    /** Opens the hosted drawer using [ratio] as a temporary size override. */
    fun open(ratio: Float)
    /** Opens the hosted drawer using [span] as a temporary size override. */
    fun open(span: Dp)

    /** Closes the hosted drawer. */
    fun close()

    /** Toggles the hosted drawer using its configured size. */
    fun toggle()
    /** Toggles the hosted drawer using [ratio] as a temporary size override when opening. */
    fun toggle(ratio: Float)
    /** Toggles the hosted drawer using [span] as a temporary size override when opening. */
    fun toggle(span: Dp)
}
