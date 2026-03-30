package majestic.graph.bazier

data class LineSeriesData(
    val label: String,
    val values: List<Float>,
    val color: LineSeriesColors,
    val dashed: Boolean = false,
    val strokeWidth: Float = 3f
)
