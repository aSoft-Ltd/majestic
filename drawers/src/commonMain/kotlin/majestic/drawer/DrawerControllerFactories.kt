package majestic.drawer

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.spring
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.rememberScreenOrientation

/**
 * Remembers a controller for the deprecated [NavigationDrawer] API.
 *
 * New code should prefer [rememberSingleDrawerController] with [InlineDrawerHost]
 * or [OverlayDrawerHost].
 *
 * @param overlap whether the drawer should float over content. Defaults to
 * `true` in portrait and `false` in landscape.
 * @param ratio size of the drawer as a fraction of the host. Defaults to `0.85`
 * in portrait and `0.35` in landscape.
 * @param animation animation used by the deprecated drawer implementation.
 * @param state initial drawer state.
 * @param direction direction used by the deprecated drawer implementation.
 */
@Deprecated(
    message = "The NavigationDrawer API is deprecated. Use rememberSingleDrawerController with InlineDrawerHost or OverlayDrawerHost instead.",
    replaceWith = ReplaceWith(
        imports = arrayOf(
            "majestic.drawer.rememberSingleDrawerController"
        ),
        expression = "rememberSingleDrawerController()",
    )
)
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
