package majestic.colors.options

import androidx.compose.ui.graphics.Color
import majestic.colors.BarColors
import majestic.colors.ColorPair
import majestic.colors.ColorRole2
import majestic.colors.ThemeColors
import majestic.colors.toColorRole

private val primary = ColorRole2(
    main = ColorPair(
        foreground = Color.White,
        background = Color(red = 0x30, green = 0xC0, blue = 0xF9),
    ),
    comp = ColorPair(
        foreground = Color.White,
        background = Color(red = 0x00, green = 0x61, blue = 0xFF),
    ),
)

private val surface1 = ColorPair(
    foreground = Color.Black,
    background = Color.White,
).toColorRole()

private val surface2 = ColorPair(
    foreground = Color.Black,
    background = Color.Gray,
).toColorRole()

private val bar = BarColors(
    top = surface1.comp,
    bottom = surface1.comp
)

val ACADEMIA_LIGHT = ThemeColors(
    primary = primary,
    surface1 = surface1,
    surface2 = surface2,
    pop = surface2.comp.toColorRole(),
    navigation = surface1.comp,
    bar = bar
)