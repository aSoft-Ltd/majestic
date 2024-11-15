package majestic.colors.options

import androidx.compose.ui.graphics.Color
import majestic.colors.ColorPair
import majestic.colors.toColorRole

private val WhiteOnBlackPair = ColorPair(
    foreground = Color.White,
    background = Color.Black,
)

private val WhiteOnBlackRole = WhiteOnBlackPair.toColorRole()
//val MONOCHROMATIC_LIGHT = ThemeColors(
//    primary = WhiteOnBlackRole,
//    surface = WhiteOnBlackRole,
//    navigation = SideNavigationMenuColors(WhiteOnBlackPair, WhiteOnBlackPair)
//)