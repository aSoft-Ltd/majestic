package majestic.graph.bazier.tools

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.unit.sp
import majestic.graph.bazier.LineChartConfig
import majestic.graph.bazier.utils.ChartLayoutInfo
import kotlin.math.roundToInt

 internal fun DrawScope.drawChartLabels(
    config: LineChartConfig,
    layout: ChartLayoutInfo,
    textMeasurer: TextMeasurer,
    ySteps: Int,
    yMin: Float,
    yRange: Float,
    xDivisor: Int,
    yTickLength: Float,
    xTickLength: Float,
    yLabelGap: Float,
    xLabelGap: Float
) {
    val axisLabelColor = config.colors.axisLabel
    val yLabelStyle = TextStyle(color = axisLabelColor, fontSize = 16.sp)
    val xLabelStyle = TextStyle(color = axisLabelColor, fontSize = 14.sp)
    val axisTitleStyle = TextStyle(color = axisLabelColor, fontSize = 16.sp)
    val xAxisTitleGap = config.xAxisTitleGap
    var maxXLabelHeight = 0f
    val yStepValue = yRange / ySteps
    val chartLeft = layout.chartLeft
    val chartBottom = layout.chartBottom

    repeat(ySteps + 1) { i ->
        val fraction = i.toFloat() / ySteps
        val y = chartBottom - (fraction * layout.chartHeight)

        if (i > 0) {
            drawLine(
                color = axisLabelColor,
                start = Offset(chartLeft - yTickLength, y),
                end = Offset(chartLeft, y),
                strokeWidth = 1f
            )
        }

        val value = (yMin + (i * yStepValue)).roundToInt().toString()
        val yLabelLayout = textMeasurer.measure(value, style = yLabelStyle)
        drawText(
            textLayoutResult = yLabelLayout,
            topLeft = Offset(
                x = chartLeft - yTickLength - yLabelGap - yLabelLayout.size.width,
                y = y - (yLabelLayout.size.height / 2f)
            )
        )
    }

    config.xLabels.forEachIndexed { i, label ->
        val x = layout.chartLeft + (i.toFloat() / xDivisor) * layout.chartWidth
        val xLabelLayout = textMeasurer.measure(label, style = xLabelStyle)
        maxXLabelHeight = maxOf(maxXLabelHeight, xLabelLayout.size.height.toFloat())
        drawText(
            textLayoutResult = xLabelLayout,
            topLeft = Offset(
                x = x - (xLabelLayout.size.width / 2f),
                y = chartBottom + xTickLength + xLabelGap
            )
        )
    }

    if (config.xAxisLabel.isNotEmpty()) {
        val axisTitle = textMeasurer.measure(config.xAxisLabel, style = axisTitleStyle)
        val titleY = chartBottom + xTickLength + xLabelGap + maxXLabelHeight + xAxisTitleGap
        drawText(
            textLayoutResult = axisTitle,
            topLeft = Offset(
                x = layout.chartLeft + (layout.chartWidth - axisTitle.size.width) / 2f,
                y = titleY
            )
        )
    }
}