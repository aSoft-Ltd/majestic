package majestic.shared.users.tools.colors.dashboard

import androidx.compose.ui.graphics.Color
import majestic.ThemeColor
import majestic.shared.tools.colors.toButtonColors
import majestic.shared.users.dashboard.HeaderColors

fun ThemeColor.toHeaderColors() = HeaderColors(
    background = Color.Transparent,
    separator = surface.contra.color.copy(.4f),
    manage = toButtonColors(),
    add = surface.contra.color,
    tint = surface.actual.color,
    title = surface.contra.color,
)