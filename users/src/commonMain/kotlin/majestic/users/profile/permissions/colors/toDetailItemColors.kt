package majestic.users.profile.permissions.colors

import majestic.ColorPair
import majestic.ThemeColor
import majestic.users.profile.permissions.detail.DetailedItemColors

internal fun ThemeColor.toDetailItemColors() = DetailedItemColors(
    leadIcon = ColorPair(
        foreground = surface.contra.color,
        background = surface.actual.color
    ),
    title = surface.contra.color,
    description = surface.contra.color.copy(.5f),
    switch = toToggleSwitchColors(),
    separator = surface.contra.color.copy(.8f)
)