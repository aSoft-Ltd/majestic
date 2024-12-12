package majestic.drawer

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.Dp

@Composable
fun rememberDrawerHostController(vararg drawers: Drawer): MultiDrawerController {
    val them = remember(drawers) {
        mutableStateMapOf<Drawer, HostedDrawerState>()
    }

    LaunchedEffect(drawers) {
        drawers.forEach { them[it] = ClosedDrawer(key = it, triggered = false) }
    }
    return MultiDrawerHostController(them)
}

@Composable
fun rememberDrawerHostController(
    position: DrawerPosition = DrawerPosition.Left,
    display: DrawerDisplay = DrawerDisplay.Overlay,
    ratio: Float,
    content: @Composable BoxScope.() -> Unit
): SingleDrawerController {
    val drawer = rememberDrawer(position, display, ratio, content)
    return rememberDrawerHostController(drawer)
}

@Composable
fun rememberDrawerHostController(
    position: DrawerPosition = DrawerPosition.Left,
    display: DrawerDisplay = DrawerDisplay.Overlay,
    span: Dp,
    content: @Composable BoxScope.() -> Unit
): SingleDrawerController {
    val drawer = rememberDrawer(position, display, span, content)
    return rememberDrawerHostController(drawer)
}