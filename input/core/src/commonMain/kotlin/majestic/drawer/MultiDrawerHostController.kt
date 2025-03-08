package majestic.drawer

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.graphics.Color

internal class MultiDrawerHostController internal constructor(
    internal val state: SnapshotStateMap<Drawer, HostedDrawerState>
) : AbstractDrawerController() {

    override fun all() = state.keys

    override fun open(
        drawer: Drawer,
        span: DrawerSpan?
    ) {
        state[drawer] = OpenedDrawer(key = drawer, span = span ?: drawer.span, display = drawer.display)
    }

    override fun close(drawer: Drawer) {
        state[drawer] = ClosedDrawer(key = drawer)
    }


    override fun toggle(drawer: Drawer, span: DrawerSpan?) {
        state[drawer] = when (val s = state[drawer]) {
            is ClosedDrawer -> OpenedDrawer(
                key = s.key,
                span = span ?: drawer.span,
                display = drawer.display
            )

            else -> ClosedDrawer(key = s?.key ?: drawer)
        }
    }

    override fun add(
        key: Any,
        span: DrawerSpan,
        position: DrawerPosition,
        display: DrawerDisplay,
        background: Color,
        content: @Composable BoxScope.(MultiDrawerController) -> Unit
    ): Drawer {
        val drawer = Drawer(span, position, display, background, content)
        state[drawer] = ClosedDrawer(key)
        return drawer
    }

    override fun find(key: Any) = state.filterValues { it.key == key }.keys

    override fun remove(drawer: Drawer?): Drawer? {
        if (!state.contains(drawer)) return null;
        state.remove(drawer)
        return drawer
    }
}