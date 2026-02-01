package majestic.shared.users.profile.tools.colors

import composex.screen.orientation.ScreenOrientation
import majestic.Light
import majestic.ThemeColor
import majestic.shared.users.profile.tools.types.DetailedHeaderColors
import majestic.shared.users.profile.tools.types.UserDetailColors
import majestic.shared.tools.colors.barColors
import majestic.shared.tools.colors.toBackground

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