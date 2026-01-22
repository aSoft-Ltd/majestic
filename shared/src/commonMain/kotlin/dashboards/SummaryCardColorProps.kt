package dashboards

import androidx.compose.ui.graphics.Color
import majestic.ColorPair

data class SummaryCardColorProps(
    val colors: ColorPair = ColorPair(
        foreground = Color.White,
        background = Color.Black
    )
)