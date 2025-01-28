package majestic

import androidx.compose.ui.graphics.Color
import majestic.colors.ColorPair

data class PaginatorColors(
    val active: ColorPair = ColorPair(
        foreground = Color.Black.copy(alpha = 0.8f),
        background = Color(0xFF30C0F9)
    ),
    val inactive: ColorPair = ColorPair(
        foreground = Color.White,
        background = Color.Transparent
    ),
    val hovered: ColorPair = ColorPair(
        foreground = Color.White,
        background = Color.White.copy(0.1f)
    ),
)
