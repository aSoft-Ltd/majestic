package majestic.shared.tools.colors

import majestic.TextFieldColors
import majestic.TextFieldMicroColors
import majestic.ThemeColor

fun ThemeColor.toTextFieldColors() = TextFieldColors(
    focused = TextFieldMicroColors(
        text = surface.contra.color,
        border = dominant.actual.color,
        placeholder = surface.contra.color.copy(alpha = 0.4f)
    ),
    blurred = TextFieldMicroColors(
        text = surface.contra.color.copy(alpha = 0.8f),
        border = surface.contra.color.copy(alpha = 0.1f),
        placeholder = surface.contra.color.copy(alpha = 0.4f)
    ),
)