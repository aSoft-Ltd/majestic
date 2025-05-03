package majestic.drawer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
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
 * Set a Host of different drawers
 *
 * Create a controller by calling [rememberDrawerHostController]
 */
@Composable
fun DrawerHost(
    controller: MultiDrawerController,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    controller as MultiDrawerHostController
    var dimension by remember { mutableStateOf(IntSize.Zero) }
    val size = dimension.toDp()
    val drawers = controller.state.toList()
    val (inlines, overlays) = drawers.partition { (drawer, state) -> state.display(drawer) == DrawerDisplay.Inline }

    Box(modifier.onPlaced { dimension = it.size }) {
        InlineDrawer(controller, inlines, size, content)
        for ((drawer, state) in overlays) {
            OverlayDrawer(controller, drawer, size, state)
        }
    }
}

@Composable
fun InlineDrawerHost(
    controller: SingleDrawerController,
    span: Dp,
    position: DrawerPosition = DrawerPosition.Left,
    modifier: Modifier = Modifier,
    drawer: @Composable BoxScope.(MultiDrawerController) -> Unit,
    content: @Composable () -> Unit
) = InlineDrawerHost(controller, DpSpan(span), position, modifier, drawer, content)

@Composable
fun InlineDrawerHost(
    controller: SingleDrawerController,
    span: Float,
    position: DrawerPosition = DrawerPosition.Left,
    modifier: Modifier = Modifier,
    drawer: @Composable BoxScope.(MultiDrawerController) -> Unit,
    content: @Composable () -> Unit
) = InlineDrawerHost(controller, RatioSpan(span), position, modifier, drawer, content)

@Composable
private fun InlineDrawerHost(
    controller: SingleDrawerController,
    span: DrawerSpan,
    position: DrawerPosition = DrawerPosition.Left,
    modifier: Modifier = Modifier,
    drawer: @Composable BoxScope.(MultiDrawerController) -> Unit,
    content: @Composable () -> Unit
) = DrawerHost(
    controller = rememberMultiDrawerHostController(controller, span, position, DrawerDisplay.Inline, Color.Transparent, drawer),
    modifier = modifier,
    content = content
)

@Composable
fun OverlayDrawerHost(
    controller: SingleDrawerController,
    span: Dp,
    position: DrawerPosition = DrawerPosition.Left,
    background: Color = Color.Transparent,
    modifier: Modifier = Modifier,
    drawer: @Composable BoxScope.(MultiDrawerController) -> Unit,
    content: @Composable () -> Unit
) = DrawerHost(
    controller = rememberMultiDrawerHostController(controller, DpSpan(span), position, DrawerDisplay.Overlay, background, drawer),
    modifier = modifier,
    content = content
)

@Composable
fun OverlayDrawerHost(
    controller: SingleDrawerController,
    ratio: Float,
    position: DrawerPosition = DrawerPosition.Left,
    background: Color = Color.Transparent,
    modifier: Modifier = Modifier,
    drawer: @Composable BoxScope.(MultiDrawerController) -> Unit,
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
    drawer: @Composable BoxScope.(MultiDrawerController) -> Unit,
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
    drawer: @Composable BoxScope.(MultiDrawerController) -> Unit,
): MultiDrawerHostController {
    controller as MultiDrawerHostController
    DisposableEffect(controller, span, position, background, drawer) {
        val key = "drawer"
        controller.add(key, span, position, display, background, drawer)
        onDispose { controller.remove(key) }
    }
    return controller
}