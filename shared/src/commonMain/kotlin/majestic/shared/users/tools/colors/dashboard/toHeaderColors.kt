package majestic.shared.users.tools.colors.dashboard

import majestic.ThemeColor
import majestic.shared.tools.colors.toButtonColors
import majestic.shared.users.dashboard.HeaderColors

fun ThemeColor.toHeaderColors(): HeaderColors = HeaderColors(
    background = surface.contra.color.copy(.01f),
    manage = toButtonColors(),
    add = surface.contra.color,
    tint = surface.actual.color,
    title = surface.contra.color,
)