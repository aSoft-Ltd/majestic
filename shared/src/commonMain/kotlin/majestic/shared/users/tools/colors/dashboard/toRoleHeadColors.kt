package majestic.shared.users.tools.colors.dashboard

import composex.screen.orientation.ScreenOrientation
import majestic.ThemeColor
import majestic.shared.tools.colors.toUserMenuOptionColors
import majestic.shared.users.dashboard.RoleHeaderColors

fun ThemeColor.toRoleHeadColors(
    orientation: ScreenOrientation
) = RoleHeaderColors(
    title = surface.contra.color,
    menu = toUserMenuOptionColors(orientation)
)