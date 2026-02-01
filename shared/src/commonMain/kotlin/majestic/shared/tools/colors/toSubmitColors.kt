package majestic.shared.tools.colors

import majestic.ThemeColor
import majestic.buttons.ButtonColors

fun ThemeColor.toSubmitColors() = ButtonColors(
    contentColor = surface.actual.color,
    containerColor = surface.contra.color
)