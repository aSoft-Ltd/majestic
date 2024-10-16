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

@Composable
fun NavigationDrawer(
    controller: DrawerController = rememberDrawerController(),
    modifier: Modifier = Modifier,
    drawer: @Composable (DrawerController) -> Unit,
    content: @Composable (DrawerController) -> Unit,
) {
    val ratio by animateFloatAsState(
        targetValue = if (controller.isOpen) controller.ratio else 0.0f,
        animationSpec = controller.animation
    )

    when {
        controller.overlap && controller.direction == DrawerOpenDirection.Right -> Box(modifier) {
            Box(modifier = Modifier.fillMaxSize()) { content(controller) }
            Box(modifier = Modifier.fillMaxHeight().fillMaxWidth(ratio)) { drawer(controller) }
        }

        controller.overlap && controller.direction == DrawerOpenDirection.Up -> Box(modifier, contentAlignment = Alignment.BottomCenter) {
            Box(modifier = Modifier.fillMaxSize()) { content(controller) }
            Box(modifier = Modifier.fillMaxWidth().fillMaxHeight(ratio)) { drawer(controller) }
        }

        !controller.overlap && controller.direction == DrawerOpenDirection.Right -> Row(modifier) {
            Box(modifier = Modifier.fillMaxHeight().fillMaxWidth(ratio)) { drawer(controller) }
            Box(modifier = Modifier.fillMaxHeight().weight(1f - ratio)) { content(controller) }
        }

        !controller.overlap && controller.direction == DrawerOpenDirection.Up -> Column(modifier) {
            Box(modifier = Modifier.fillMaxWidth().weight(1f - ratio)) { content(controller) }
            Box(modifier = Modifier.fillMaxWidth().fillMaxHeight(ratio)) { drawer(controller) }
        }
    }
}
