package majestic.shared.appbar.colors

import androidx.compose.ui.graphics.Color
import majestic.ColorPair
import majestic.ThemeColor

fun ThemeColor.toAppBarActionColors(isClose: Boolean = false, isHovered: Boolean = false): ColorPair {
    if (isClose && isHovered) {
        return ColorPair(
            foreground = Color.White,
            background = Color(0xFFE53935),
        )
    } else if (isHovered) {
        return ColorPair(
            foreground = surface.contra.color,
            background = surface.contra.color.copy(0.05f),
        )
    }
    return ColorPair(
        foreground = surface.contra.color,
        background = Color.Transparent,
    )
}
