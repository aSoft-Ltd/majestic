package majestic.shared.users.tools.colors.profile.permissions

import majestic.ColorPair
import majestic.ThemeColor
import majestic.shared.profiles.permissions.PermissionColors
import majestic.shared.tools.colors.toBackground


fun ThemeColor.toPermissionColors(): PermissionColors = PermissionColors(
    leadIcon = ColorPair(
        foreground = surface.contra.color,
        background = surface.actual.color
    ),
    title = surface.contra.color,
    description = surface.contra.color.copy(.5f),
    trailIcon = surface.contra.color.copy(.5f),
    separator = surface.contra.color.copy(.8f),
    background = toBackground.copy(.5f)
)