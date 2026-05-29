package majestic.drawer

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**
 * A remembered drawer definition that can be registered in a [DrawerHost].
 *
 * Consumers normally create drawer instances with [rememberInlineDrawer] or
 * [rememberOverlayDrawer] instead of constructing this class directly. The host
 * uses the drawer's internal span, position, display mode, background, and
 * content when it renders and controls the drawer.
 */
class Drawer internal constructor(
    internal val span: DrawerSpan,
    internal val position: DrawerPosition,
    internal val display: DrawerDisplay,
    internal val background: Color,
    internal val content: @Composable BoxScope.(DrawerContext) -> Unit
)
