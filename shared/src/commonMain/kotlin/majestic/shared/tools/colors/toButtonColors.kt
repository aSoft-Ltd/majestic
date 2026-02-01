package majestic.shared.tools.colors

import majestic.ThemeColor
import majestic.buttons.ButtonColors

fun ThemeColor.toButtonColors(): ButtonColors = ButtonColors(
    contentColor = surface.contra.color,
    containerColor = surface.contra.color.copy(0.05f)
)