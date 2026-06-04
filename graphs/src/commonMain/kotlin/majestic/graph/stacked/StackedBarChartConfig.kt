package majestic.graph.stacked

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class StackedGridConfig(
    val showHorizontalLines: Boolean = true,
    val showVerticalLines: Boolean = false,
    val showXAxisLine: Boolean = true,
    val showYAxisLine: Boolean = false,
    val showDots: Boolean = false,
    val showTicks: Boolean = false,
    val isDashed: Boolean = true
)

data class StackedChartPadding(
    val left: Dp = 50.dp,
    val right: Dp = 24.dp,
    val top: Dp = 10.dp,
    val bottom: Dp = 40.dp
)

data class StackedBarChartConfig(
    val data: List<StackedBarData>,
    val stackLabels: List<String>,
    val title: String = "",
    val yMin: Float = 0f,
    val yMax: Float? = null,
    val ySteps: Int = 6,
    val grid: StackedGridConfig = StackedGridConfig(),
    val padding: StackedChartPadding = StackedChartPadding(),
    val colors: StackedBarColors
)
