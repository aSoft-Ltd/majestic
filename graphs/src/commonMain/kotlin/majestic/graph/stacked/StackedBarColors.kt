package majestic.graph.stacked

import androidx.compose.ui.graphics.Color

data class StackedBarColors(
    val background: Color = Color.Transparent,
    val axisLabel: Color = Color(0xFF888899),
    val grid: Color = Color(0xFF2A2A3E),
    val stacks: List<Color>
)
