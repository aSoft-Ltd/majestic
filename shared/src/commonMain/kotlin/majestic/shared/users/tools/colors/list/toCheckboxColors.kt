package majestic.shared.users.tools.colors.list

import androidx.compose.ui.graphics.Color
import majestic.CheckboxColors
import majestic.CheckboxMicroColors
import majestic.ColorPair
import majestic.ThemeColor

fun ThemeColor.toCheckboxColors() = CheckboxColors(
    selected = CheckboxMicroColors(
        background = Color.Transparent,
        border = Color.Transparent,
        icon = ColorPair(
            background = dominant.actual.color,
            foreground = dominant.contra.color
        )
    ),
    unselected = CheckboxMicroColors(
        background = Color.Transparent,
        border = surface.contra.color.copy(0.3f),
        icon = ColorPair(
            background = dominant.actual.color,
            foreground = dominant.contra.color
        )
    )
)