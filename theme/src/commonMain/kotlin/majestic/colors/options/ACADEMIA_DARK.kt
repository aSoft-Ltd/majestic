package majestic.colors.options

import androidx.compose.ui.graphics.Color
import majestic.colors.BarColors
import majestic.colors.ColorPair
import majestic.colors.ColorRole2
import majestic.colors.ThemeColors
import majestic.colors.toColorRole

private val primary = ColorPair(
    foreground = Color(0xFFFAFAFA),
    background = Color(0xFF0061FF),
//    background = Color(0xFFFF5722),
//    background = Color(0xFF673AB7),
//    background = Color.Yellow,
//    background = Color.Magenta
).toColorRole()

private val surface1 = ColorRole2(
    main = ColorPair(
        foreground = Color(0xFFFAFAFA),
        background = Color(0xFF15181D),
    ),
    comp = ColorPair(
        foreground = Color(0xFFFAFAFA),
        background = Color(0xFF151C25)
    )
)

private val surface2 = ColorRole2(
    main = ColorPair(
        foreground = Color(0xFFFAFAFA),
        background = Color(0xFF1D2430),
    ),
    comp = ColorPair(
        foreground = Color(0xFFFAFAFA),
        background = Color(0xFF232B3A),
    )
)

private val bar = BarColors(
    top = surface1.comp,
    bottom = surface1.comp
)

val ACADEMIA_DARK = ThemeColors(
    primary = primary,
    surface1 = surface1,
    surface2 = surface2,
    pop = surface2,
    navigation = surface1.comp,
    bar = bar
)