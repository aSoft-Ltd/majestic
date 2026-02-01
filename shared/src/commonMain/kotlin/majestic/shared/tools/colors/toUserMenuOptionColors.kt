package majestic.shared.tools.colors

import composex.screen.orientation.ScreenOrientation
import majestic.ThemeColor
import majestic.shared.menu.MenuOptionColors

fun ThemeColor.toUserMenuOptionColors(orientation: ScreenOrientation) = MenuOptionColors(
    icon = toMenuOptionColors(orientation = orientation).icon,
    dropDown = toMenuOptionColors(orientation = orientation).dropDown,
    item = toMenuOptionColors(orientation = orientation).item,
    lastItem = toMenuOptionColors(orientation = orientation).lastItem
)
