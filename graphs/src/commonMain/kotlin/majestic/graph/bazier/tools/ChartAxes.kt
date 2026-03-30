package majestic.graph.bazier.tools

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawScope
import majestic.graph.bazier.LineChartConfig
import majestic.graph.bazier.utils.ChartLayoutInfo
import kotlin.math.round

private fun Float.snapToStrokeCenter(strokeWidth: Float = 1f): Float {
    val halfStroke = strokeWidth / 2f
    return round(this - halfStroke) + halfStroke
}

 internal fun DrawScope.drawChartAxes(
    config: LineChartConfig,
    layout: ChartLayoutInfo,
    xDivisor: Int,
    xTickLength: Float
) {
    val strokeWidth = 1f
    val originX = layout.chartLeft.snapToStrokeCenter(strokeWidth)
    val originY = layout.chartBottom.snapToStrokeCenter(strokeWidth)
    val topY = layout.chartTop.snapToStrokeCenter(strokeWidth)
    val rightX = layout.chartRight.snapToStrokeCenter(strokeWidth)

    drawLine(
        color = config.colors.axisLabel,
        start = Offset(originX, topY),
        end = Offset(originX, originY),
        strokeWidth = strokeWidth
    )

    drawLine(
        color = config.colors.axisLabel,
        start = Offset(originX, originY),
        end = Offset(rightX, originY),
        strokeWidth = strokeWidth
    )

    config.xLabels.forEachIndexed { i, _ ->
        if (i == 0) return@forEachIndexed
        val x = (layout.chartLeft + (i.toFloat() / xDivisor) * layout.chartWidth).snapToStrokeCenter(strokeWidth)
        drawLine(
            color = config.colors.axisLabel,
            start = Offset(x, originY),
            end = Offset(x, originY + xTickLength),
            strokeWidth = strokeWidth
        )
    }
}
