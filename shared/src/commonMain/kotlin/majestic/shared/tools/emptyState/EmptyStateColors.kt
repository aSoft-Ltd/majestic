package majestic.shared.tools.emptyState

import androidx.compose.ui.graphics.Color
import majestic.ColorPair

data class EmptyStateColors(
    val foreground: Color,
    val button: ColorPair,
    val iconBlur: Color,
    val iconBackdrop: Color = Color.Transparent
)