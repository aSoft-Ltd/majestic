package majestic

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import majestic.ColorPair

data class PaginatorColors(
    val active: ColorPair = ColorPair(
        foreground = Color.Black.copy(alpha = 0.8f),
        background = Color.White.copy(0.2f),
        backgroundModifier = Modifier
    ),
    val inactive: ColorPair = ColorPair(
        foreground = Color.White,
        background = Color.Transparent,
        backgroundModifier = Modifier
    ),
    val hovered: ColorPair = ColorPair(
        foreground = Color.White,
        background = Color.White.copy(0.1f),
        backgroundModifier = Modifier
    ),
)
