package majestic.shared.tools.colors

import androidx.compose.ui.graphics.Color
import composex.screen.orientation.ScreenOrientation
import majestic.ColorPair
import majestic.ThemeColor
import majestic.navigation.MenuItemColors
import majestic.shared.menu.MenuOptionColors


fun ThemeColor.toInActive(isRed: Boolean) = ColorPair(
    background = Color.Transparent,
    foreground = when {
        isRed -> Color(0xFFEF5350)
        else -> surface.contra.color.copy(alpha = 0.7f)
    }
)

fun ThemeColor.toHover(isRed: Boolean) = ColorPair(
    background = when {
        isRed -> Color(0xFFEF5350).copy(alpha = 0.2f)
        else -> surface.actual.color.copy(alpha = 0.5f)
    },
    foreground = when {
        isRed -> Color(0xFFEF5350)
        else -> surface.contra.color
    }
)

fun ThemeColor.toSelected(isRed: Boolean) = ColorPair(
    background = Color.Transparent,
    foreground = when {
        isRed -> Color(0xFFEF5350)
        else -> surface.contra.color
    }
)

fun ThemeColor.toMenuOptionColors(isRed: Boolean = false) = MenuItemColors(
    inactive = toInActive(isRed),
    selected = toSelected(isRed),
    hovered = toHover(isRed)
)

fun ThemeColor.toMenuOptionColors() = MenuItemColors(
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

fun ThemeColor.toMenuOptionColors(orientation: ScreenOrientation) = MenuOptionColors(
    icon = toIconColors(orientation),
    dropDown = toPopMainColors().background,
    item = toMenuOptionColors(),
    lastItem = toMenuOptionColors(true)
)