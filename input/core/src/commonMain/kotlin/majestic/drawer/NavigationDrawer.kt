package majestic.drawer

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier

@Composable
fun NavigationDrawer(
    controller: DrawerController = rememberDrawerController(),
    modifier: Modifier = Modifier,
    drawer: @Composable (DrawerController) -> Unit,
    content: @Composable (DrawerController) -> Unit,
) {
    val ratio by animateFloatAsState(if (controller.isOpen) controller.ratio else 0.0f)

    if (!controller.overlap) Row(modifier) {
        Box(modifier = Modifier.fillMaxHeight().fillMaxWidth(ratio)) {
            drawer(controller)
        }
        Box(modifier = Modifier.fillMaxHeight().weight(1f - ratio)) {
            content(controller)
        }
    } else Box(modifier) {
        Box(modifier = Modifier.fillMaxSize()) {
            content(controller)
        }
        Box(modifier = Modifier.fillMaxHeight().fillMaxWidth(ratio)) {
            drawer(controller)
        }
    }
}
