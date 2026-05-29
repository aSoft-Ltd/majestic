package majestic.drawer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember

/**
 * Creates a controller for a [DrawerHost] and registers the supplied drawers as closed.
 *
 * Use this for screens that need to host one or more drawers created with
 * [rememberInlineDrawer] or [rememberOverlayDrawer].
 *
 * @param drawers drawers that should be known to the host immediately.
 */
@Composable
fun rememberDrawerHostController(vararg drawers: Drawer): MultiDrawerController {
    val them = remember(drawers) {
        mutableStateMapOf<Drawer, HostedDrawerState>()
    }

    LaunchedEffect(drawers) {
        drawers.forEach { them[it] = ClosedDrawer(key = it) }
    }
    return MultiDrawerHostController(them)
}

/**
 * Creates a controller suitable for [InlineDrawerHost] or [OverlayDrawerHost].
 *
 * The returned controller still uses the same host implementation, but exposes
 * the simpler [SingleDrawerController] API for screens that only need to control
 * one drawer.
 */
@Composable
fun rememberSingleDrawerController(): SingleDrawerController = rememberDrawerHostController()
