@file:OptIn(ExperimentalComposeUiApi::class)

package composex.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.IntSize

@Composable
actual fun rememberScreenSizeInPx(): IntSize = LocalWindowInfo.current.containerSize