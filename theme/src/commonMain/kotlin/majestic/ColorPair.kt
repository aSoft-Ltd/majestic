package majestic

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

data class ColorPair(
    val foreground: Color,
    val background: Color,
    val backgroundModifier: Modifier = Modifier
) {
    fun copy(alpha: Float): ColorPair = ColorPair(
        foreground = foreground.copy(alpha = alpha),
        background = background.copy(alpha = alpha),
        backgroundModifier = backgroundModifier
    )

    fun blend(alpha: Float) = ColorPair(
        foreground = foreground.copy(foreground.alpha * alpha),
        background = background.copy(background.alpha * alpha),
        backgroundModifier = backgroundModifier
    )

    fun flip(): ColorPair = ColorPair(
        foreground = background,
        background = foreground,
        backgroundModifier = backgroundModifier
    )
}