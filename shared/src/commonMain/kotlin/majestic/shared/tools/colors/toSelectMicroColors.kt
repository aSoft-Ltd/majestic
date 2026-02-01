package majestic.shared.tools.colors

import majestic.ColorPair
import majestic.SelectColors
import majestic.SelectMicroColors
import majestic.ThemeColor

fun ThemeColor.toSelectMicroColors() = SelectMicroColors(
    border = toBackground.copy(.7f),
    placeholder = toBackground.copy(.7f),
    text = surface.contra.color.copy(.7f)
)

fun ThemeColor.toSelectColors() = SelectColors(
    focused = toSelectMicroColors(),
    blurred = toSelectMicroColors(),
    dropdown = ColorPair(
        background = toBackground,
        foreground = surface.contra.color
    )
)