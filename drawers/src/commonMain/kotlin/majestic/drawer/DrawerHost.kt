package majestic.drawer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import majestic.drawer.host.InlineDrawer
import majestic.drawer.host.OverlayDrawer
import majestic.drawer.host.display
import majestic.drawer.host.toDp

/**
 * Hosts one or more inline and overlay drawers around [content].
 *
 * Create drawers with [rememberInlineDrawer] or [rememberOverlayDrawer], register
 * them with [rememberDrawerHostController], then pass that controller here. Inline
 * drawers are rendered as part of layout, while overlay drawers are rendered
 * above the content.
 *
 * @param controller controller that owns the drawer registrations and state.
 * Defaults to [LocalDrawerHostControllerContext], which is mainly useful for
 * nested drawer-aware composition.
 * @param modifier modifier applied to the host container.
 * @param content main screen content wrapped by the drawer host.
 */
@Composable
fun DrawerHost(
    controller: MultiDrawerController = LocalDrawerHostControllerContext.current,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    controller as MultiDrawerHostController
    var dimension by remember(controller) { mutableStateOf(IntSize.Zero) }
    val size = dimension.toDp()
    val drawers = controller.state.toList()
    val (inlines, overlays) = drawers.partition { (drawer, state) -> state.display(drawer) == DrawerDisplay.Inline }

    CompositionLocalProvider(LocalDrawerHostControllerContext provides controller) {
        Box(modifier.onPlaced { dimension = it.size }) {
            InlineDrawer(controller, inlines, size, content)
            for ((drawer, state) in overlays) {
                OverlayDrawer(controller, drawer, size, state)
            }
        }
    }
}

/**
 * Hosts a single inline drawer with a fixed [Dp] span.
 *
 * Inline drawers participate in layout and reflow the host content while open.
 *
 * @param controller controller returned by [rememberSingleDrawerController].
 * @param span fixed drawer width for left/right drawers, or height for top/bottom drawers.
 * @param position edge from which the drawer opens. Defaults to [DrawerPosition.Left].
 * @param modifier modifier applied to the host container.
 * @param drawer drawer body content.
 * @param content main screen content.
 */
@Composable
fun InlineDrawerHost(
    controller: SingleDrawerController,
    span: Dp,
    position: DrawerPosition = DrawerPosition.Left,
    modifier: Modifier = Modifier,
    drawer: @Composable BoxScope.(DrawerContext) -> Unit,
    content: @Composable () -> Unit
) = InlineDrawerHost(controller, DpSpan(span), position, modifier, drawer, content)

/**
 * Hosts a single inline drawer whose size is calculated as a ratio of the host.
 *
 * Inline drawers participate in layout and reflow the host content while open.
 *
 * @param controller controller returned by [rememberSingleDrawerController].
 * @param span fraction of the host width or height used as the drawer span.
 * @param position edge from which the drawer opens. Defaults to [DrawerPosition.Left].
 * @param modifier modifier applied to the host container.
 * @param drawer drawer body content.
 * @param content main screen content.
 */
@Composable
fun InlineDrawerHost(
    controller: SingleDrawerController,
    span: Float,
    position: DrawerPosition = DrawerPosition.Left,
    modifier: Modifier = Modifier,
    drawer: @Composable BoxScope.(DrawerContext) -> Unit,
    content: @Composable () -> Unit
) = InlineDrawerHost(controller, RatioSpan(span), position, modifier, drawer, content)

@Composable
private fun InlineDrawerHost(
    controller: SingleDrawerController,
    span: DrawerSpan,
    position: DrawerPosition = DrawerPosition.Left,
    modifier: Modifier = Modifier,
    drawer: @Composable BoxScope.(DrawerContext) -> Unit,
    content: @Composable () -> Unit
) = DrawerHost(
    controller = rememberMultiDrawerHostController(controller, span, position, DrawerDisplay.Inline, Color.Transparent, drawer),
    modifier = modifier,
    content = content
)

/**
 * Hosts a single overlay drawer with a fixed [Dp] span.
 *
 * Overlay drawers float above the host content. The [background] value controls
 * the overlay backdrop behind the drawer.
 *
 * @param controller controller returned by [rememberSingleDrawerController].
 * @param span fixed drawer width for left/right drawers, or height for top/bottom drawers.
 * @param position edge from which the drawer opens. Defaults to [DrawerPosition.Left].
 * @param background overlay backdrop color. Defaults to [Color.Transparent].
 * @param modifier modifier applied to the host container.
 * @param drawer drawer body content.
 * @param content main screen content.
 */
@Composable
fun OverlayDrawerHost(
    controller: SingleDrawerController,
    span: Dp,
    position: DrawerPosition = DrawerPosition.Left,
    background: Color = Color.Transparent,
    modifier: Modifier = Modifier,
    drawer: @Composable BoxScope.(DrawerContext) -> Unit,
    content: @Composable () -> Unit
) = DrawerHost(
    controller = rememberMultiDrawerHostController(controller, DpSpan(span), position, DrawerDisplay.Overlay, background, drawer),
    modifier = modifier,
    content = content
)

/**
 * Hosts a single overlay drawer whose size is calculated as a ratio of the host.
 *
 * Overlay drawers float above the host content. The [background] value controls
 * the overlay backdrop behind the drawer.
 *
 * @param controller controller returned by [rememberSingleDrawerController].
 * @param ratio fraction of the host width or height used as the drawer span.
 * @param position edge from which the drawer opens. Defaults to [DrawerPosition.Left].
 * @param background overlay backdrop color. Defaults to [Color.Transparent].
 * @param modifier modifier applied to the host container.
 * @param drawer drawer body content.
 * @param content main screen content.
 */
@Composable
fun OverlayDrawerHost(
    controller: SingleDrawerController,
    ratio: Float,
    position: DrawerPosition = DrawerPosition.Left,
    background: Color = Color.Transparent,
    modifier: Modifier = Modifier,
    drawer: @Composable BoxScope.(DrawerContext) -> Unit,
    content: @Composable () -> Unit
) = DrawerHost(
    controller = rememberMultiDrawerHostController(controller, RatioSpan(ratio), position, DrawerDisplay.Overlay, background, drawer),
    modifier = modifier,
    content = content
)

@Composable
private fun OverlayDrawerHost(
    controller: SingleDrawerController,
    span: DrawerSpan,
    position: DrawerPosition = DrawerPosition.Left,
    background: Color = Color.Transparent,
    modifier: Modifier = Modifier,
    drawer: @Composable BoxScope.(DrawerContext) -> Unit,
    content: @Composable () -> Unit
) = DrawerHost(
    controller = rememberMultiDrawerHostController(controller, span, position, DrawerDisplay.Overlay, background, drawer),
    modifier = modifier,
    content = content
)

@Composable
private fun rememberMultiDrawerHostController(
    controller: SingleDrawerController,
    span: DrawerSpan,
    position: DrawerPosition,
    display: DrawerDisplay,
    background: Color,
    drawer: @Composable BoxScope.(DrawerContext) -> Unit,
): MultiDrawerHostController {
    controller as MultiDrawerHostController
    DisposableEffect(controller, span, position, background, drawer) {
        val key = "drawer"
        controller.add(key, span, position, display, background, drawer)
        onDispose { controller.remove(key) }
    }
    return controller
}