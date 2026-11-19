package majestic.dialogs

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape

val LocalWindowShape = staticCompositionLocalOf<Shape> { RectangleShape }