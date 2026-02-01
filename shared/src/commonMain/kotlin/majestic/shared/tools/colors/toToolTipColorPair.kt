package majestic.shared.tools.colors

import majestic.ColorPair
import majestic.ThemeColor

fun ThemeColor.toToolTipColorPair() = ColorPair(
    background = surface.contra.color,
    foreground = surface.actual.color
)