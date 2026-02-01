package majestic.shared.users.tools.colors.dashboard

import majestic.ThemeColor
import majestic.shared.tools.colors.toPopCompColors
import majestic.shared.users.dashboard.PortraitHeaderColors

internal fun ThemeColor.toTabColors() = PortraitHeaderColors(
    background = toPopCompColors().foreground.copy(.3f),
    manage = toButtonDashboardColors(),
    add = surface.contra.color,
    tint = surface.actual.color,
    title = surface.contra.color,
    tab = toTabItemColors()
)