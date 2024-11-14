package majestic.colors.options

import androidx.compose.ui.graphics.Color
import majestic.colors.BarColors
import majestic.colors.ColorPair
import majestic.colors.ColorRole
import majestic.colors.ThemeColors
import majestic.colors.toColorRole

private val primary = ColorPair(
    foreground = Color.White,
    background = Color(red = 18, green = 39, blue = 68),
).toColorRole()

private val surface = ColorRole(
    main = ColorPair(
        foreground = Color.White,
        background = Color(red = 21, green = 23, blue = 30),
    ),
    comp = ColorPair(
        foreground = Color.White,
        background = Color(red = 20, green = 29, blue = 37)
    )
)

private val bar = BarColors(
    top = ColorPair(
        background = Color(red = 27, green = 37, blue = 51),
        foreground = Color.White
    ),
    bottom = ColorPair(
        background = Color(red = 20, green = 26, blue = 34),
        foreground = Color.White
    )
)

val ACADEMIA_DARK = ThemeColors(
    primary = primary,
    surface = surface,
    navigation = surface.comp,
    bar = bar
)