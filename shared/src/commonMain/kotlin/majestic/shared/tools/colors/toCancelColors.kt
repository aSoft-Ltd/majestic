package majestic.shared.tools.colors

import majestic.ThemeColor
import majestic.buttons.ButtonColors

fun ThemeColor.toCancelColors(): ButtonColors = ButtonColors(
    contentColor = surface.contra.color,
    containerColor = toBackground.copy(alpha = 0.2f)
)