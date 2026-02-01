package majestic.shared.users.tools.colors.dashboard

import majestic.ThemeColor
import majestic.shared.users.dashboard.CardStatColors


fun ThemeColor.toCardStatColors() = CardStatColors(
    tint = surface.contra.color.copy(.7f),
    title = surface.contra.color.copy(.7f),
    value = surface.contra.color
)