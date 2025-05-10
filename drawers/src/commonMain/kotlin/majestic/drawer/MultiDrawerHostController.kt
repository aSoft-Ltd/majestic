package majestic.drawer

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import kotlin.properties.ReadOnlyProperty

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
            is OpenedDrawer -> ClosedDrawer(key = s.key)
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
        content: @Composable BoxScope.(DrawerContext) -> Unit
    ): Drawer {
        val drawer = Drawer(span, position, display, background, content)
        state[drawer] = ClosedDrawer(key)
        return drawer
    }

    override fun add(
        ratio: Float,
        position: DrawerPosition,
        display: DrawerDisplay,
        background: Color,
        content: @Composable BoxScope.(DrawerContext) -> Unit
    ) = ReadOnlyProperty<Any?, Drawer> { _, property ->
        val key = property.name
        val drawer = Drawer(RatioSpan(ratio), position, display, background, content)
        state[drawer] = ClosedDrawer(key)
        drawer
    }

    override fun add(
        span: Dp,
        position: DrawerPosition,
        display: DrawerDisplay,
        background: Color,
        content: @Composable BoxScope.(DrawerContext) -> Unit
    ) = ReadOnlyProperty<Any?, Drawer> { _, property ->
        val key = property.name
        val drawer = Drawer(DpSpan(span), position, display, background, content)
        state[drawer] = ClosedDrawer(key)
        drawer
    }

    private fun checkState(drawer: Any) = when (state[drawer]) {
        is ClosedDrawer -> DrawerState.Closed
        is OpenedDrawer -> DrawerState.Open
        null -> DrawerState.Closed
    }

    override fun state(drawer: Any): DrawerState = checkState(drawer)

    override fun state(drawer: Drawer): DrawerState = checkState(drawer)

    override fun find(key: Any) = state.filterValues { it.key == key }.keys

    override fun remove(drawer: Drawer?): Drawer? {
        if (!state.contains(drawer)) return null;
        state.remove(drawer)
        return drawer
    }
}