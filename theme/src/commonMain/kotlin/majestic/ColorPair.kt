package majestic

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

data class ColorPair(
    val foreground: Color,
    val background: Color,
) {
    fun copy(alpha: Float): ColorPair = ColorPair(
        foreground = foreground.copy(alpha = alpha),
        background = background.copy(alpha = alpha),
    )

    fun blend(alpha: Float) = ColorPair(
        foreground = foreground.copy(foreground.alpha * alpha),
        background = background.copy(background.alpha * alpha),
    )

    fun flip(): ColorPair = ColorPair(
        foreground = background,
        background = foreground,
    )
}