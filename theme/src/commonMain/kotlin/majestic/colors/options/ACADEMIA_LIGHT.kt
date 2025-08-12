package majestic.colors.options

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import majestic.colors.BarColors
import majestic.ColorPair
import majestic.colors.ColorRole2
import majestic.colors.ThemeColors
import majestic.colors.toColorRole

private val primary = ColorRole2(
    main = ColorPair(
        foreground = Color.Black,
//        background = Color(0xFFC5DAF8),
        background = Color(0xFF0061FF),
    ),
    comp = ColorPair(
        foreground = Color.Black,
        background = Color(0xFF0061FF),
    ),
)

private val surface1 = ColorRole2(
    main = ColorPair(
        foreground = Color(0xFF161616),
        background = Color(0xFFF7FAFF)
    ),
    comp = ColorPair(
        foreground = Color(0xFF161616),
        background = Color(0xFFE8EEF7)
    )
)

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
    surface1 = surface1.copy(
        comp = surface1.comp.copy(background = lerp(surface1.comp.background, primary.main.background.copy(0.05f), 1f))
    ),
    surface2 = surface2,
    pop = surface2.comp.toColorRole(),
    navigation = surface1.comp,
    bar = bar
)

fun academiaLight(primary: ColorPair) = ThemeColors(
    primary = primary.toColorRole(),
    surface1 = surface1.copy(
        comp = surface1.comp.copy(background = lerp(surface1.comp.background, primary.background.copy(0.05f), 1f))
    ),
    surface2 = surface2,
    pop = surface2.comp.toColorRole(),
    navigation = surface1.comp,
    bar = bar
)

fun academiaLight(color: Color) = academiaLight(ColorPair(foreground = Color(0xFF161616), background = color))
