package majestic.shared.tools.colors

import majestic.ColorPair
import majestic.Dark
import majestic.Light
import majestic.ThemeColor

fun ThemeColor.toFloatingButtonColors() = when (this) {
    is Light -> ColorPair(
        foreground = dominant.contra.color.copy(alpha = 1f),
        background = dominant.actual.color.copy(alpha = 1f)
    )

    is Dark -> ColorPair(
        foreground = surface.actual.color.copy(alpha = 1f),
        background = surface.contra.color.copy(alpha = 1f)
    )
}