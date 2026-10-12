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
            background = if (background == Color.Transparent)
                Color.Transparent
            else
                background.copy(alpha = 0.4f),
            foreground = foreground.copy(alpha = 0.4f)
        )
    )
}