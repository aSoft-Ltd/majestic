@file:OptIn(ExperimentalComposeUiApi::class)

package composex.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalWindowInfo
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation

@Composable
actual fun rememberScreenOrientation(): ScreenOrientation {
    val window = LocalWindowInfo.current.containerSize
    return if (window.width > window.height) Landscape else Portrait
}