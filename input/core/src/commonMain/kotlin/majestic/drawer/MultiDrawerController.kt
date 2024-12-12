package majestic.drawer

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

interface MultiDrawerController : SingleDrawerController {
    fun open(drawer: Drawer)
    fun open(drawer: Any)

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
        content: @Composable BoxScope.() -> Unit
    ): Drawer

    fun add(
        key: Any,
        span: Dp,
        position: DrawerPosition = DrawerPosition.Right,
        display: DrawerDisplay = DrawerDisplay.Overlay,
        background: Color = Color.Transparent,
        content: @Composable BoxScope.() -> Unit
    ): Drawer


    fun remove(drawer: Drawer?): Drawer?
    fun remove(key: Any): List<Drawer>
}