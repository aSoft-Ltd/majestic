package majestic.drawer

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.spring
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.rememberScreenOrientation


@Composable
fun rememberDrawerController(
    overlap: Boolean? = null,
    ratio: Float? = null,
    animation: AnimationSpec<Float> = spring(),
    state: DrawerState = DrawerState.Open,
    direction: DrawerOpenDirection = DrawerOpenDirection.Right,
): DrawerController {
    val orientation = rememberScreenOrientation()
    val o = overlap ?: (orientation is Portrait)
    val r = ratio ?: when (orientation) {
        Portrait -> 0.85f
        Landscape -> 0.35f
    }
    val controller = remember { DrawerController(state, o, animation, r, direction) }
    LaunchedEffect(ratio) {
        controller.ratio.value = ratio ?: when (orientation) {
            Portrait -> 0.85f
            Landscape -> 0.35f
        }
    }

    LaunchedEffect(direction) {
        controller.direction.value = direction
    }

    LaunchedEffect(overlap) {
        controller.overlap.value = overlap ?: (orientation is Portrait)
    }

    LaunchedEffect(state) {
        controller.state.value = state
    }

    return controller
}