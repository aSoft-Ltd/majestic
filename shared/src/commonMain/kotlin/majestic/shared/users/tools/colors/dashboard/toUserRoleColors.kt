package majestic.shared.users.tools.colors.dashboard

import composex.screen.orientation.ScreenOrientation
import majestic.ThemeColor
import majestic.shared.tools.colors.toBackground
import majestic.shared.users.dashboard.UserRoleColors

fun ThemeColor.toUserRoleColors(
    orientation: ScreenOrientation
) = UserRoleColors(
    background = toBackground,
    header = toHeaderColors(),
    roleCard = toRoleCardColors(orientation)
)