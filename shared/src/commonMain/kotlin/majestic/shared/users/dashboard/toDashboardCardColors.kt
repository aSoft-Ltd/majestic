package majestic.shared.users.dashboard

import majestic.ColorPair
import majestic.ThemeColor
import majestic.shared.tools.colors.toBackground


fun ThemeColor.toDashboardCardColors() = ColorPair(
    foreground = surface.contra.color,
    background = toBackground.copy(.5f)
)