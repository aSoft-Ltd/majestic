package majestic.graph.stacked.tools

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.graph.stacked.StackedBarChartConfig
import majestic.graph.stacked.utils.StackedChartLayoutInfo
import kotlin.math.roundToInt

internal fun DrawScope.drawStackedLabels(
    config: StackedBarChartConfig,
    layout: StackedChartLayoutInfo,
    textMeasurer: TextMeasurer,
    ySteps: Int,
    yMin: Float,
    yRange: Float,
    yLabelGap: Float
) {
    val axisLabelColor = config.colors.axisLabel
    val yLabelStyle = TextStyle(color = axisLabelColor.copy(alpha = 0.7f), fontSize = 12.sp)
    val xLabelStyle = TextStyle(color = axisLabelColor.copy(alpha = 0.9f), fontSize = 11.sp)

    val yStepValue = yRange / ySteps
    val chartLeft = layout.chartLeft
    val chartBottom = layout.chartBottom

    repeat(ySteps + 1) { i ->
        val fraction = i.toFloat() / ySteps
        val y = chartBottom - (fraction * layout.chartHeight)

        if (config.grid.showTicks && i > 0) {
            drawLine(
                color = axisLabelColor.copy(alpha = 0.3f),
                start = Offset(chartLeft - 5.dp.toPx(), y),
                end = Offset(chartLeft, y),
                strokeWidth = 1f
            )
        }

        val value = (yMin + (i * yStepValue)).roundToInt().toString()
        val yLabelLayout = textMeasurer.measure(value, style = yLabelStyle)
        drawText(
            textLayoutResult = yLabelLayout,
            topLeft = Offset(
                x = chartLeft - yLabelGap - yLabelLayout.size.width,
                y = y - (yLabelLayout.size.height / 2f)
            )
        )
    }

    val dataSize = config.data.size
    if (dataSize > 0) {
        val slotWidth = layout.chartWidth / dataSize
        config.data.forEachIndexed { i, item ->
            val slotX = chartLeft + i * slotWidth
            val xLabelLayout = textMeasurer.measure(item.label, style = xLabelStyle)
            drawText(
                textLayoutResult = xLabelLayout,
                topLeft = Offset(
                    x = slotX + (slotWidth - xLabelLayout.size.width) / 2f,
                    y = chartBottom + 10.dp.toPx()
                )
            )
        }
    }
}
