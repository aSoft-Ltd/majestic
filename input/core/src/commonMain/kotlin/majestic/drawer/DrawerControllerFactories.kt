package majestic.drawer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.rememberScreenOrientation


@Composable
fun rememberDrawerController(
    overlap: Boolean? = null,
    ratio: Float? = null,
    state: DrawerState = DrawerState.Open
): DrawerController {
    val orientation = rememberScreenOrientation()
    return remember(orientation) {
        val o = overlap ?: (orientation is Portrait)
        val r = ratio ?: when (orientation) {
            Portrait -> 0.85f
            Landscape -> 0.35f
        }
        DrawerController(state, o, r)
    }
}