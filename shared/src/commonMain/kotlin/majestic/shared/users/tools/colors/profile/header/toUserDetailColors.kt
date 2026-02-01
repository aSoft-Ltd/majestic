package majestic.shared.users.tools.colors.profile.header

import composex.screen.orientation.ScreenOrientation
import majestic.Light
import majestic.ThemeColor
import majestic.shared.tools.colors.barColors
import majestic.shared.tools.colors.toBackground
import majestic.shared.users.profile.DetailedHeaderColors
import majestic.shared.users.profile.UserDetailColors

fun ThemeColor.toUserDetailColors(
    orientation: ScreenOrientation
) = UserDetailColors(
    clientBackground = toBackground,
    barColors = barColors(orientation),
    detailHeader = DetailedHeaderColors(
        icon = if (this.mode is Light) dominant.contra.color else surface.contra.color,
        head = toProfileHeaderColors(orientation = orientation)
    )
)