package majestic.drawer

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * Legacy single navigation drawer composable.
 *
 * Prefer [InlineDrawerHost] or [OverlayDrawerHost] for new single-drawer code.
 *
 * @param controller legacy drawer controller. Defaults to [rememberDrawerController].
 * @param modifier modifier applied to the drawer container.
 * @param drawer drawer body content.
 * @param content main content displayed with or behind the drawer.
 */
@Deprecated(
    message = "The NavigationDrawer API is deprecated. Use InlineDrawerHost or OverlayDrawerHost with rememberSingleDrawerController instead.",
    replaceWith = ReplaceWith(
        imports = arrayOf(
            "majestic.drawer.OverlayDrawerHost",
            "majestic.drawer.rememberSingleDrawerController"
        ),
        expression = "OverlayDrawerHost(controller = rememberSingleDrawerController(), span = 0.85f, drawer = drawer, content = content)",
    )
)
@Composable
fun NavigationDrawer(
    controller: DrawerController = rememberDrawerController(),
    modifier: Modifier = Modifier,
    drawer: @Composable (DrawerController) -> Unit,
    content: @Composable (DrawerController) -> Unit,
) {
    val r by controller.ratio
    val ratio by animateFloatAsState(
        targetValue = if (controller.isOpen) r else 0.0f,
        animationSpec = controller.animation
    )

    val overlap by controller.overlap
    val direction by controller.direction

    when {
        overlap && direction == DrawerOpenDirection.Right -> Box(modifier) {
            Box(modifier = Modifier.fillMaxSize()) { content(controller) }
            Box(modifier = Modifier.fillMaxHeight().fillMaxWidth(ratio)) { drawer(controller) }
        }

        overlap && direction == DrawerOpenDirection.Up -> Box(modifier, contentAlignment = Alignment.BottomCenter) {
            Box(modifier = Modifier.fillMaxSize()) { content(controller) }
            Box(modifier = Modifier.fillMaxWidth().fillMaxHeight(ratio)) { drawer(controller) }
        }

        !overlap && direction == DrawerOpenDirection.Right -> Row(modifier) {
            Box(modifier = Modifier.fillMaxHeight().fillMaxWidth(ratio)) { drawer(controller) }
            Box(modifier = Modifier.fillMaxHeight().weight(1f - ratio)) { content(controller) }
        }

        !overlap && direction == DrawerOpenDirection.Up -> Column(modifier) {
            Box(modifier = Modifier.fillMaxWidth().weight(1f - ratio)) { content(controller) }
            Box(modifier = Modifier.fillMaxWidth().fillMaxHeight(ratio)) { drawer(controller) }
        }
    }
}