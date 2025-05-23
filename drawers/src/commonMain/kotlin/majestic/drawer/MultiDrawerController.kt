package majestic.drawer

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import kotlin.properties.ReadOnlyProperty

interface MultiDrawerController : SingleDrawerController {
    fun open(drawer: Drawer)
    fun open(drawer: Any)

    fun open(vararg drawers: Drawer)

    fun open(drawer: Drawer, ratio: Float)
    fun open(drawer: Any, ratio: Float)

    fun open(drawer: Drawer, span: Dp)
    fun open(drawer: Any, span: Dp)


    fun close(drawer: Drawer)
    fun close(drawer: Any)


    fun toggle(drawer: Drawer)
    fun toggle(drawer: Any)

    fun toggle(drawer: Drawer, ratio: Float)
    fun toggle(drawer: Any, ratio: Float)

    fun toggle(drawer: Drawer, span: Dp)
    fun toggle(drawer: Any, span: Dp)

    fun add(
        drawer: Any,
        ratio: Float,
        position: DrawerPosition = DrawerPosition.Right,
        display: DrawerDisplay = DrawerDisplay.Overlay,
        background: Color = Color.Transparent,
        content: @Composable BoxScope.(DrawerContext) -> Unit
    ): Drawer

    fun add(
        ratio: Float,
        position: DrawerPosition = DrawerPosition.Right,
        display: DrawerDisplay = DrawerDisplay.Overlay,
        background: Color = Color.Transparent,
        content: @Composable BoxScope.(DrawerContext) -> Unit
    ): ReadOnlyProperty<Any?, Drawer>

    fun add(
        key: Any,
        span: Dp,
        position: DrawerPosition = DrawerPosition.Right,
        display: DrawerDisplay = DrawerDisplay.Overlay,
        background: Color = Color.Transparent,
        content: @Composable BoxScope.(DrawerContext) -> Unit
    ): Drawer

    fun add(
        span: Dp,
        position: DrawerPosition = DrawerPosition.Right,
        display: DrawerDisplay = DrawerDisplay.Overlay,
        background: Color = Color.Transparent,
        content: @Composable BoxScope.(DrawerContext) -> Unit
    ): ReadOnlyProperty<Any?, Drawer>

    fun state(drawer: Drawer): DrawerState
    fun state(drawer: Any): DrawerState

    fun remove(drawer: Drawer?): Drawer?
    fun remove(key: Any): List<Drawer>
}