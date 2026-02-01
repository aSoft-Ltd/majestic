package majestic.shared.users.tools.colors.dashboard

import androidx.compose.ui.graphics.compositeOver
import majestic.ThemeColor
import majestic.buttons.ButtonColors
import majestic.shared.tools.colors.toBackground

fun ThemeColor.toButtonDashboardColors() = ButtonColors(
    contentColor = surface.contra.color,
    containerColor = toBackground.copy(.9f)
        .compositeOver(surface.contra.color.copy(.8f))
)