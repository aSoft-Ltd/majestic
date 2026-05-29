package majestic.drawer

import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.staticCompositionLocalOf

/**
 * Composition local used by [DrawerHost] to expose the active [MultiDrawerController].
 *
 * Most screen-level code should pass its controller to [DrawerHost] explicitly.
 * This local is useful for nested drawer-aware composables that need access to
 * the current host controller without threading it through every parameter.
 */
val LocalDrawerHostControllerContext = staticCompositionLocalOf<MultiDrawerController>{
    MultiDrawerHostController(mutableStateMapOf())
}
