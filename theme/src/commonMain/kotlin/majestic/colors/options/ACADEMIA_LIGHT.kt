package majestic.colors.options

import androidx.compose.ui.graphics.Color
import majestic.colors.BarColors
import majestic.colors.ColorPair
import majestic.colors.ColorRole
import majestic.colors.ThemeColors
import majestic.colors.toColorRole

private val primary = ColorRole(
    main = ColorPair(
        foreground = Color.White,
        background = Color(red = 0x30, green = 0xC0, blue = 0xF9),
    ),
    comp = ColorPair(
        foreground = Color.White,
        background = Color(red = 0x00, green = 0x61, blue = 0xFF),
    ),
)

private val surface = ColorPair(
    foreground = Color.Black,
    background = Color.White,
).toColorRole()

private val bar = BarColors(
    top = surface.comp,
    bottom = surface.comp
)

val ACADEMIA_LIGHT = ThemeColors(
    primary = primary,
    surface = surface,
    navigation = surface.comp,
    bar = bar
)