package majestic.graph.stacked.utils

internal data class StackedChartLayoutInfo(
    val widthPx: Float,
    val heightPx: Float,
    val paddingLeft: Float,
    val paddingRight: Float,
    val paddingTop: Float,
    val paddingBottom: Float,
    val chartWidth: Float,
    val chartHeight: Float,
) {
    val chartLeft: Float get() = paddingLeft
    val chartTop: Float get() = paddingTop
    val chartRight: Float get() = paddingLeft + chartWidth
    val chartBottom: Float get() = paddingTop + chartHeight
}

internal fun calculateStackedChartLayoutInfo(
    widthPx: Float,
    heightPx: Float,
    paddingLeft: Float,
    paddingRight: Float,
    paddingTop: Float,
    paddingBottom: Float
): StackedChartLayoutInfo {
    val chartWidth = (widthPx - paddingLeft - paddingRight).coerceAtLeast(0f)
    val chartHeight = (heightPx - paddingTop - paddingBottom).coerceAtLeast(0f)

    return StackedChartLayoutInfo(
        widthPx = widthPx,
        heightPx = heightPx,
        paddingLeft = paddingLeft,
        paddingRight = paddingRight,
        paddingTop = paddingTop,
        paddingBottom = paddingBottom,
        chartWidth = chartWidth,
        chartHeight = chartHeight
    )
}
