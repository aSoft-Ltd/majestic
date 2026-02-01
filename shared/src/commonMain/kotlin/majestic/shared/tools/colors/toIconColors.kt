package majestic.shared.tools.colors

import composex.screen.orientation.ScreenOrientation
import majestic.ColorPair
import majestic.ThemeColor

internal fun ThemeColor.toIconColors(orientation: ScreenOrientation) = ColorPair(
    background = surface.contra.color.copy(0.1f),
    foreground = surface.contra.color
)