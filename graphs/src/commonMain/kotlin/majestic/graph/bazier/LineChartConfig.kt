package majestic.graph.bazier

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class GridConfig(
    val showHorizontalLines: Boolean = false,
    val showVerticalLines: Boolean = false
)

data class ChartPadding(
    val left: Dp = 62.dp,
    val right: Dp = 16.dp,
    val top: Dp = 8.dp,
    val bottom: Dp = 64.dp
)

data class LineChartColors(
    val background: Color = Color.Transparent,
    val axisLabel: Color = Color(0xFF888899),
    val grid: Color = Color(0xFF2A2A3E)
)

data class LineChartConfig(
    val series: List<LineSeriesData>,
    val xLabels: List<String>,
    val yAxisLabel: String = "",
    val xAxisLabel: String = "",
    val yMin: Float = 0f,
    val yMax: Float? = null,
    val ySteps: Int = 6,
    val grid: GridConfig = GridConfig(),
    val padding: ChartPadding = ChartPadding(),
    val colors: LineChartColors = LineChartColors(),
    val dashIntervals: FloatArray = floatArrayOf(12f, 8f),
    val xAxisTitleGap: Float = 10f
)
