package majestic

import androidx.compose.ui.graphics.Color

data class PaginatorColors(
    val page: PageButton = PageButton(),
    val direction: DirectionButton = DirectionButton(),
) {
    data class PageButton(
        val active: ColorPair = ColorPair(
            foreground = Color.Black.copy(alpha = 0.8f),
            background = Color.White.copy(0.2f),
        ),
        val inactive: ColorPair = ColorPair(
            foreground = Color.White,
            background = Color.Transparent,
        ),
        val hovered: ColorPair = ColorPair(
            foreground = Color.White,
            background = Color.White.copy(0.1f),
        ),
    )

    data class DirectionButton(
        val inactive: ColorPair = ColorPair(
            foreground = Color.White,
            background = Color.Transparent,
        ),
        val hovered: ColorPair = ColorPair(
            foreground = Color.White,
            background = Color.White.copy(0.1f),
        ),
    )
}
