package majestic.shared.users.tools.colors.profile.permissions

import androidx.compose.ui.graphics.Color
import majestic.ThemeColor
import majestic.shared.profiles.permissions.detail.BreadCrumbTabColors
import majestic.shared.tools.colors.toBackground

fun ThemeColor.toBreadCrumbTabColors(tint: Color? = null) = BreadCrumbTabColors(
    tint = tint ?: surface.contra.color,
    background = toBackground,
    label = surface.contra.color
)