package majestic.drawer

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onPlaced
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
    alignment: Alignment = Alignment.TopStart,
    content: @Composable () -> Unit,
) {
    controller as MultiDrawerHostController
    var dimension by remember { mutableStateOf(IntSize.Zero) }
    val size = dimension.toDp()
    val drawers = controller.state.toList()
    val (inlines, overlays) = drawers.partition { (drawer, state) -> state.display(drawer) == DrawerDisplay.Inline }
    Box(modifier.onPlaced { dimension = it.size }, alignment) {
        InlineDrawer(inlines, size, content)
        for ((drawer, state) in overlays) {
            OverlayDrawer(drawer, size, state)
        }
    }
}