package majestic.drawer

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@Composable
fun rememberOverlayDrawer(
    ratio: Float,
    position: DrawerPosition = DrawerPosition.Left,
    background: Color = Color.Transparent,
    content: @Composable BoxScope.(DrawerContext) -> Unit
): Drawer = rememberDrawer(RatioSpan(ratio), position, DrawerDisplay.Overlay, background, content)

@Composable
fun rememberOverlayDrawer(
    span: Dp,
    position: DrawerPosition = DrawerPosition.Left,
    background: Color = Color.Transparent,
    content: @Composable BoxScope.(DrawerContext) -> Unit
): Drawer = rememberDrawer(DpSpan(span), position, DrawerDisplay.Overlay, background, content)

@Composable
fun rememberInlineDrawer(
    ratio: Float,
    position: DrawerPosition = DrawerPosition.Left,
    content: @Composable BoxScope.(DrawerContext) -> Unit
): Drawer = rememberDrawer(RatioSpan(ratio), position, DrawerDisplay.Inline, Color.Transparent, content)

@Composable
fun rememberInlineDrawer(
    span: Dp,
    position: DrawerPosition = DrawerPosition.Left,
    content: @Composable BoxScope.(DrawerContext) -> Unit
): Drawer = rememberDrawer(DpSpan(span), position, DrawerDisplay.Inline, Color.Transparent, content)

@Composable
private fun rememberDrawer(
    span: DrawerSpan,
    position: DrawerPosition,
    display: DrawerDisplay,
    background: Color,
    content: @Composable BoxScope.(DrawerContext) -> Unit
): Drawer = remember(span, position, display, background, content) {
    Drawer(span, position, display, background, content)
}