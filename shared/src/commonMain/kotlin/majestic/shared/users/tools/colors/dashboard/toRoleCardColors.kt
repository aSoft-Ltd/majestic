package majestic.shared.users.tools.colors.dashboard

import composex.screen.orientation.ScreenOrientation
import majestic.ThemeColor
import majestic.shared.users.dashboard.RoleCardColors

fun ThemeColor.toRoleCardColors(orientation: ScreenOrientation) = RoleCardColors(
    head = toRoleHeadColors(orientation),
    body = surface.contra.color.copy(.7f),
    footer = toCardStatColors(),
)
