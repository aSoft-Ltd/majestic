package majestic.drawer

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

/**
 * Remembers an overlay drawer whose size is calculated as a ratio of the host.
 *
 * Overlay drawers are rendered above the host content. The backdrop is drawn
 * behind the drawer and does not replace the drawer body's own background.
 * Style the drawer body from inside [content].
 *
 * @param ratio fraction of the host width or height used as the drawer span.
 * @param position edge from which the drawer opens. Defaults to [DrawerPosition.Left].
 * @param backdrop overlay backdrop color. Defaults to [Color.Transparent].
 * @param content drawer body content.
 */
@Composable
fun rememberOverlayDrawer(
    ratio: Float,
    position: DrawerPosition = DrawerPosition.Left,
    backdrop: Color = Color.Transparent,
    content: @Composable BoxScope.(DrawerContext) -> Unit,
): Drawer = rememberDrawer(RatioSpan(ratio), position, DrawerDisplay.Overlay, backdrop, content)

/**
 * Remembers an overlay drawer with a fixed [Dp] span.
 *
 * Overlay drawers are rendered above the host content. The backdrop is drawn
 * behind the drawer and does not replace the drawer body's own background.
 * Style the drawer body from inside [content].
 *
 * @param span fixed drawer width for horizontal drawers, or height for vertical drawers.
 * @param position edge from which the drawer opens. Defaults to [DrawerPosition.Left].
 * @param backdrop overlay backdrop color. Defaults to [Color.Transparent].
 * @param content drawer body content.
 */
@Composable
fun rememberOverlayDrawer(
    span: Dp,
    position: DrawerPosition = DrawerPosition.Left,
    backdrop: Color = Color.Transparent,
    content: @Composable BoxScope.(DrawerContext) -> Unit,
): Drawer = rememberDrawer(DpSpan(span), position, DrawerDisplay.Overlay, backdrop, content)

/**
 * Remembers an inline drawer whose size is calculated as a ratio of the host.
 *
 * Inline drawers participate in layout, so the host content reflows when the
 * drawer opens. Inline drawers do not have an overlay backdrop; style the drawer
 * body from inside [content].
 *
 * @param ratio fraction of the host width or height used as the drawer span.
 * @param position edge from which the drawer opens. Defaults to [DrawerPosition.Left].
 * @param content drawer body content.
 */
@Composable
fun rememberInlineDrawer(
    ratio: Float,
    position: DrawerPosition = DrawerPosition.Left,
    content: @Composable BoxScope.(DrawerContext) -> Unit,
): Drawer = rememberDrawer(RatioSpan(ratio), position, DrawerDisplay.Inline, Color.Transparent, content)

/**
 * Remembers an inline drawer with a fixed [Dp] span.
 *
 * Inline drawers participate in layout, so the host content reflows when the
 * drawer opens. Inline drawers do not have an overlay backdrop; style the drawer
 * body from inside [content].
 *
 * @param span fixed drawer width for horizontal drawers, or height for vertical drawers.
 * @param position edge from which the drawer opens. Defaults to [DrawerPosition.Left].
 * @param content drawer body content.
 */
@Composable
fun rememberInlineDrawer(
    span: Dp,
    position: DrawerPosition = DrawerPosition.Left,
    content: @Composable BoxScope.(DrawerContext) -> Unit,
): Drawer = rememberDrawer(DpSpan(span), position, DrawerDisplay.Inline, Color.Transparent, content)

@Composable
private fun rememberDrawer(
    span: DrawerSpan,
    position: DrawerPosition,
    display: DrawerDisplay,
    backdrop: Color,
    content: @Composable BoxScope.(DrawerContext) -> Unit,
): Drawer = remember(span, position, display, backdrop, content) {
    Drawer(span, position, display, backdrop, content)
}
