package majestic.button

import androidx.compose.ui.graphics.Color
import majestic.ColorPair

internal fun ColorPair.toButtonParams(): ButtonParams<ColorPair> {
    val pair = ColorPair(background = background, foreground = foreground)
    return ButtonParams(
        default = pair,
        hovered = pair,
        pressed = pair,
        disabled = ColorPair(
            background = background.copy(alpha = if (background == Color.Transparent) 1f else 0.4f),
            foreground = foreground.copy(alpha = 0.4f)
        )
    )
}