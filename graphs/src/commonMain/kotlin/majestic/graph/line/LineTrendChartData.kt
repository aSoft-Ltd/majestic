package majestic.graph.line

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class LineTrendPoint(
    val index: Int,
    val value: Float,
    val color: Color? = null
)

@Immutable
data class LineTrendSeries(
    val name: String,
    val abbreviation: String,
    val color: Color,
    val points: List<LineTrendPoint>,
    val dashed: Boolean = false,
    val showPoints: Boolean = false,
    val pointFill: Color? = null
)

@Immutable
data class LineTrendChartColors(
    val foreground: Color,
    val axis: Color,
    val grid: Color,
    val tick: Color,
    val label: Color
)
