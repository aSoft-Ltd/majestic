package users

import majestic.ColorPair
import majestic.SearchDefaultColors
import majestic.SelectColors
import majestic.ThemeColor
import majestic.navigation.MenuItemColors

data class HeaderInnerColors(
    val theme: ThemeColor,
    val compPopColors: ColorPair,
    val selectColors: SelectColors,
    val search: SearchDefaultColors,
    val menuItem: MenuItemColors
)