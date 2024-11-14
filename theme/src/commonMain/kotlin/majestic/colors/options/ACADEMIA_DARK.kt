package majestic.colors.options

import androidx.compose.ui.graphics.Color
import majestic.colors.ColorPair
import majestic.colors.ColorRole
import majestic.colors.ThemeColors

private val primary = ColorRole(
    main = ColorPair(
        foreground = Color.White,
        background = Color(red = 18, green = 39, blue = 68),
    ),
    comp = ColorPair(
        foreground = Color.White,
        background = Color(red = 0x00, green = 0x61, blue = 0xFF),
    )
)

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

val ACADEMIA_DARK = ThemeColors(primary, surface, surface.comp)