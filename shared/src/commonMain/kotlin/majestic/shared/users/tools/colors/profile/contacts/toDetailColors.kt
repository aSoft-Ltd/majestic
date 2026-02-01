package majestic.shared.users.tools.colors.profile.contacts

import androidx.compose.ui.graphics.compositeOver
import majestic.ThemeColor
import majestic.shared.profiles.permissions.detail.DetailColors
import majestic.shared.users.tools.colors.profile.permissions.toBreadCrumbTabColors

fun ThemeColor.toDetailColors(): DetailColors = DetailColors(
    tint = dominant.actual.color.copy(alpha = 0.4f)
        .compositeOver(surface.contra.color),
    background = surface.contra.color,
    breadCrumb = { toBreadCrumbTabColors(it) },
    detail = toDetailItemColors()
)