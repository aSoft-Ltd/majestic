package majestic.payments.tools.colors

import androidx.compose.ui.graphics.Color
import majestic.ChoiceColorGroup
import majestic.ChoiceColors
import majestic.ColorPair
import majestic.ThemeColor

internal fun ThemeColor.toChoiceColors() = ChoiceColors(
    selected = ChoiceColorGroup(
        background = dominant.actual.color.copy(alpha = 0.2f),
        label = surface.contra.color,
        border = Color.Transparent,
        icon = ColorPair(
            background = dominant.actual.color,
            foreground = surface.actual.color
        )
    ),
    unselected = ChoiceColorGroup(
        background = Color.Transparent,
        label = surface.contra.color,
        border = surface.contra.color.copy(alpha = 0.6f),
        icon = ColorPair(
            background = Color.Transparent,
            foreground = Color.Transparent
        )
    )
)
