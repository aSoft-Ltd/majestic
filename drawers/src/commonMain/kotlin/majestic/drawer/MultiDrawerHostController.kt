package majestic.drawer

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

internal class MultiDrawerHostController internal constructor(
    internal val state: SnapshotStateMap<Drawer, HostedDrawerState>
) : AbstractDrawerController() {

    override fun all() = state.keys

    override fun open(
        drawer: Drawer,
        span: DrawerSpan?
    ) {
        if (state[drawer] is OpenedDrawer) return
        state[drawer] = drawer.toOpened(span)
    }

    private fun Drawer.key() = state[this]?.key ?: this

    private fun Drawer.cleanKey(): Any {
        val key = key()
        val existing = state.entries.filter { (d, s) -> s.key == key }.map { it.key }
        existing.forEach { state.remove(it) }
        return key
    }

    private fun Drawer.toOpened(span: DrawerSpan?): OpenedDrawer {
        val key = key()
        val existing = state.entries.filter { (d, s) -> s.key == key }
        println("Found: ${existing.size} existing")
        existing.map { it.key }.forEach { state.remove(it) }
        println("existing: ${existing.map { it.value.key }}")
//        state.filterValues { it.key == key }
        return OpenedDrawer(key = key, span = span ?: this.span, display = this.display)
    }

    private fun Drawer.toClosed(): ClosedDrawer {
        val key = state[this]?.key ?: this
        state.remove(key)
        return ClosedDrawer(key = key)
    }

    override fun open(vararg drawers: Drawer) {
        state.putAll(drawers.associateWith { it.toOpened(null) })
    }

    override fun close(drawer: Drawer) {
        if (state[drawer] is ClosedDrawer) return
        state[drawer] = drawer.toClosed()
    }


    override fun toggle(drawer: Drawer, span: DrawerSpan?) {
        state[drawer] = if (state[drawer] is ClosedDrawer) drawer.toOpened(span) else drawer.toClosed()
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

    private fun KProperty<*>.createKey(): String {
        var key = name
        var count = 0
        while (state.values.any { it.key == key }) {
            key = "$name-${count++}"
        }
        return key
    }

    private fun KProperty<*>.getOrCreate(creator: () -> Drawer): Drawer {
        val exists = find(name).firstOrNull()
        if (exists != null) return exists
        val drawer = creator()
        state[drawer] = ClosedDrawer(createKey())
        return drawer
    }

    override fun add(
        ratio: Float,
        position: DrawerPosition,
        display: DrawerDisplay,
        background: Color,
        content: @Composable BoxScope.(DrawerContext) -> Unit
    ) = ReadOnlyProperty<Any?, Drawer> { _, property ->
        property.getOrCreate { Drawer(RatioSpan(ratio), position, display, background, content) }
    }

    override fun add(
        span: Dp,
        position: DrawerPosition,
        display: DrawerDisplay,
        background: Color,
        content: @Composable BoxScope.(DrawerContext) -> Unit
    ) = ReadOnlyProperty<Any?, Drawer> { _, property ->
        property.getOrCreate { Drawer(DpSpan(span), position, display, background, content) }
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
        if (!state.contains(drawer)) return null
        state.remove(drawer)
        return drawer
    }
}