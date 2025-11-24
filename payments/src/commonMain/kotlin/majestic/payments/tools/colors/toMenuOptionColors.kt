package majestic.payments.tools.colors

import androidx.compose.ui.graphics.Color
import majestic.ColorPair
import majestic.ThemeColor
import majestic.navigation.MenuItemColors

internal fun ThemeColor.toMenuOptionColors() = MenuItemColors(
    inactive = ColorPair(
        background = Color.Transparent,
        foreground = toPopMainColors().foreground.copy(alpha = 0.6f)
    ),
    selected = ColorPair(
        background = Color.Transparent,
        foreground = toPopMainColors().foreground
    ),
    hovered = ColorPair(
        background = toPopCompColors().background,
        foreground = toPopCompColors().foreground.copy(alpha = 0.8f)
    )
)
