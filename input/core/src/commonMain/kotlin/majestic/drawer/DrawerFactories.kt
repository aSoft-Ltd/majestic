package majestic.drawer

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.Dp

@Composable
fun rememberDrawer(
    position: DrawerPosition = DrawerPosition.Left,
    display: DrawerDisplay = DrawerDisplay.Overlay,
    ratio: Float,
    content: @Composable BoxScope.() -> Unit
): Drawer = rememberDrawer(position, RatioSpan(ratio), display, content)

@Composable
fun rememberDrawer(
    position: DrawerPosition = DrawerPosition.Left,
    display: DrawerDisplay = DrawerDisplay.Overlay,
    span: Dp,
    content: @Composable BoxScope.() -> Unit
): Drawer = rememberDrawer(position, DpSpan(span), display, content)

@Composable
private fun rememberDrawer(
    position: DrawerPosition,
    span: DrawerSpan,
    display: DrawerDisplay,
    content: @Composable BoxScope.() -> Unit
): Drawer = remember(position, span, display, content) {
    Drawer(position, span, display, content)
}