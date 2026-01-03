package majestic.users.profile.permissions.colors

import androidx.compose.ui.graphics.Color
import majestic.ThemeColor
import majestic.users.profile.permissions.detail.BreadCrumbTabColors
import majestic.users.tools.colors.toBackground

internal fun ThemeColor.toBreadCrumbTabColors(tint: Color? = null) = BreadCrumbTabColors(
    tint = tint ?: surface.contra.color,
    background = toBackground,
    label = surface.contra.color
)