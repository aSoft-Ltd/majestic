package majestic.drawer

import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.staticCompositionLocalOf

val LocalDrawerHostControllerContext = staticCompositionLocalOf<MultiDrawerController>{
    MultiDrawerHostController(mutableStateMapOf())
}