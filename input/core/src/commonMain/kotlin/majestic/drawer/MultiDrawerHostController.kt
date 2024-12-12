package majestic.drawer

import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.unit.Dp

class MultiDrawerHostController internal constructor(
    internal val state: SnapshotStateMap<Drawer, HostedDrawerState>
) {
    fun open() = state.keys.forEach { open(it, null) }
    fun open(ratio: Float) = state.keys.forEach { open(it, RatioSpan(ratio)) }
    fun open(span: Dp) = state.keys.forEach { open(it, DpSpan(span)) }
    private fun open(span: DrawerSpan) = state.keys.forEach { open(it, span) }

    fun open(drawer: Drawer) = open(drawer, null)

    fun open(drawer: Drawer, ratio: Float) = open(drawer, RatioSpan(ratio))

    fun open(drawer: Drawer, span: Dp) = open(drawer, DpSpan(span))

    private fun open(
        drawer: Drawer,
        span: DrawerSpan?
    ) {
        state[drawer] = OpenedDrawer(span = span ?: drawer.span, display = drawer.display)
    }

    fun close(drawer: Drawer) {
        state[drawer] = ClosedDrawer
    }

    fun close() = state.keys.forEach { close(it) }

    fun toggle() = state.keys.forEach { toggle(it, null) }
    fun toggle(ratio: Float) = state.keys.forEach { toggle(it, RatioSpan(ratio)) }
    fun toggle(span: Dp) = state.keys.forEach { toggle(it, DpSpan(span)) }

    fun toggle(drawer: Drawer) = toggle(drawer, null)
    fun toggle(drawer: Drawer, ratio: Float) = toggle(drawer, RatioSpan(ratio))
    fun toggle(drawer: Drawer, span: Dp) = toggle(drawer, DpSpan(span))

    private fun toggle(drawer: Drawer, span: DrawerSpan?) {
        state[drawer] = when (state[drawer]) {
            is ClosedDrawer -> OpenedDrawer(
                span = span ?: drawer.span,
                display = drawer.display
            )

            else -> ClosedDrawer
        }
    }

}