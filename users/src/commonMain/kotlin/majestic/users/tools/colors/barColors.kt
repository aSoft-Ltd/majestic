package majestic.users.tools.colors

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.ColorPair
import majestic.DarkMode
import majestic.LightMode
import majestic.ThemeColor

val ThemeColor.barBackgroundColor: Color
    get() {
        return dominant.actual.color.copy(alpha = 0.06f).compositeOver(surface.actual.color)
    }

fun ThemeColor.barColors(orientation: ScreenOrientation) = ColorPair(
    background = when (orientation) {
        is Landscape -> barBackgroundColor
        is Portrait -> when (mode) {
            DarkMode -> barBackgroundColor
            LightMode -> dominant.actual.color
        }
    },
    foreground = surface.contra.color.copy(alpha = 0.8f)
)