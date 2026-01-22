package menu

import androidx.compose.ui.graphics.Color
import majestic.ColorPair
import majestic.navigation.MenuItemColors

data class MenuOptionColors(
    val icon: ColorPair = ColorPair(
        foreground = Color.White,
        background = Color.Transparent
    ),
    val dropDown: Color = Color.White,
    val item: MenuItemColors = MenuItemColors(),
    val lastItem: MenuItemColors = MenuItemColors()
) {
    companion object {
        val default by lazy { MenuOptionColors() }
    }
}