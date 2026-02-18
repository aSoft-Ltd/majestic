package majestic.shared.menu

import androidx.compose.ui.graphics.Color
import majestic.ColorPair
import majestic.navigation.MenuItemColors

data class MenuOptionColors(
    val icon: ColorPair,
    val dropDown: Color,
    val item: MenuItemColors,
    val lastItem: MenuItemColors,
    val listIconButton: ListIconButtonColors
) {
    companion object {
        val default by lazy {
            MenuOptionColors(
                icon = ColorPair(
                    foreground = Color.White,
                    background = Color.Transparent
                ),
                dropDown = Color.White,
                item = MenuItemColors(),
                lastItem = MenuItemColors(),
                listIconButton = ListIconButtonColors()
            )
        }
    }
}

data class ListIconButtonColors(
    val foreground: Color = Color.White,
    val tint: Color = Color.Blue
) {
    companion object {
        val default by lazy { ListIconButtonColors() }
    }
}