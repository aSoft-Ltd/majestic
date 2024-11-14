package majestic.colors.options

import androidx.compose.ui.graphics.Color
import majestic.colors.ColorPair
import majestic.colors.SideNavigationMenuColors
import majestic.colors.ThemeColors
import majestic.colors.toColorRole

private val BlackOnWhitePair = ColorPair(
    foreground = Color.Black,
    background = Color.White,
)

private val BlackOnWhiteRole = BlackOnWhitePair.toColorRole()

//val MONOCHROMATIC_DARK = ThemeColors(
//    primary = BlackOnWhiteRole,
//    surface = BlackOnWhiteRole,
//    navigation = SideNavigationMenuColors(side = BlackOnWhitePair, bottom = BlackOnWhitePair)
//)