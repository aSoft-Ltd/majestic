package majestic.drawer

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

internal abstract class AbstractDrawerController : MultiDrawerController {
    abstract fun all(): Collection<Drawer>
    override fun open() = all().forEach { open(it, null) }
    override fun open(ratio: Float) = all().forEach { open(it, RatioSpan(ratio)) }
    override fun open(span: Dp) = all().forEach { open(it, DpSpan(span)) }

    override fun open(drawer: Drawer) = open(drawer, null)

    override fun open(drawer: Any) = find(drawer).forEach { open(it, null) }

    override fun open(drawer: Drawer, ratio: Float) = open(drawer, RatioSpan(ratio))
    override fun open(drawer: Any, ratio: Float) = find(drawer).forEach { open(it, RatioSpan(ratio)) }

    override fun open(drawer: Drawer, span: Dp) = open(drawer, DpSpan(span))
    override fun open(drawer: Any, span: Dp) = find(drawer).forEach { open(it, DpSpan(span)) }

    abstract fun open(
        drawer: Drawer,
        span: DrawerSpan?
    )

    override fun close(drawer: Any) = find(drawer).forEach { close(it) }

    override fun close() = all().forEach { close(it) }

    override fun toggle() = all().forEach { toggle(it, null) }
    override fun toggle(ratio: Float) = all().forEach { toggle(it, RatioSpan(ratio)) }
    override fun toggle(span: Dp) = all().forEach { toggle(it, DpSpan(span)) }

    override fun toggle(drawer: Drawer) = toggle(drawer, null)
    override fun toggle(drawer: Any) {
        find(drawer).forEach { toggle(it, null) }
    }

    override fun toggle(drawer: Drawer, ratio: Float) = toggle(drawer, RatioSpan(ratio))
    override fun toggle(drawer: Any, ratio: Float) {
        find(drawer).forEach { toggle(it, RatioSpan(ratio)) }
    }

    override fun toggle(drawer: Drawer, span: Dp) = toggle(drawer, DpSpan(span))
    override fun toggle(drawer: Any, span: Dp) {
        find(drawer).forEach { toggle(it, DpSpan(span)) }
    }

    abstract fun toggle(drawer: Drawer, span: DrawerSpan?)

    override fun add(
        drawer: Any,
        ratio: Float,
        position: DrawerPosition,
        display: DrawerDisplay,
        background: Color,
        content: @Composable (BoxScope.(DrawerContext) -> Unit)
    ) = add(drawer, RatioSpan(ratio), position, display, background, content)

    override fun add(
        key: Any,
        span: Dp,
        position: DrawerPosition,
        display: DrawerDisplay,
        background: Color,
        content: @Composable (BoxScope.(DrawerContext) -> Unit)
    ) = add(key, DpSpan(span), position, display, background, content)

    abstract fun add(
        key: Any,
        span: DrawerSpan,
        position: DrawerPosition,
        display: DrawerDisplay,
        background: Color,
        content: @Composable BoxScope.(DrawerContext) -> Unit
    ): Drawer

    abstract fun find(key: Any): Set<Drawer>


    override fun remove(key: Any): List<Drawer> = find(key).mapNotNull { remove(drawer = it) }
}