package majestic.shared.appbar.colors

import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.ColorPair
import majestic.DarkMode
import majestic.LightMode
import majestic.ThemeColor
import majestic.tools.withNormal

fun ThemeColor.toAppBarColors(orientation: ScreenOrientation) = ColorPair(
    background = when (orientation) {
        is Landscape -> surface.actual.color.withNormal(dominant.actual.color, 0.05f)
        is Portrait -> when (mode) {
            DarkMode -> surface.actual.color.withNormal(dominant.actual.color, 0.05f)
            LightMode -> dominant.actual.color
        }
    },
    foreground = when (orientation) {
        is Landscape -> surface.contra.color.copy(alpha = 0.8f)
        is Portrait -> when (mode) {
            DarkMode -> surface.contra.color.copy(alpha = 0.8f)
            LightMode -> dominant.contra.color.copy(alpha = 0.8f)
        }
    }
)
