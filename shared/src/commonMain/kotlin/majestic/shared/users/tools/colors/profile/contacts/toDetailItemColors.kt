package majestic.shared.users.tools.colors.profile.contacts

import majestic.ColorPair
import majestic.ThemeColor
import majestic.shared.profiles.permissions.detail.DetailedItemColors
import majestic.shared.users.tools.colors.profile.security.toToggleSwitchColors

fun ThemeColor.toDetailItemColors() = DetailedItemColors(
    leadIcon = ColorPair(
        foreground = surface.contra.color,
        background = surface.actual.color
    ),
    title = surface.contra.color,
    description = surface.contra.color.copy(.5f),
    switch = toToggleSwitchColors(),
    separator = surface.contra.color.copy(.8f)
)