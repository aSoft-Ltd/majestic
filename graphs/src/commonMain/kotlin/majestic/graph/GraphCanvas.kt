package majestic.graph

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.graph.tools.Series

data class TransactionGroup(
    val label: String,
    val values: List<Float>
)

@Composable
fun GraphCanvas(
    axisColor: Color,
    textColor: Color,
    steps: Int,
    stepSymbol: String,
    series: List<Series>,
    groups: List<TransactionGroup>,
    modifier: Modifier = Modifier
) {
    require(groups.all { it.values.size == series.size }) {
        "Each TransactionGroup.values must have the same size as series"
    }

    val textMeasurer = rememberTextMeasurer()
    val textStyle = remember {
        TextStyle(
            color = textColor,
            fontSize = 10.sp
        )
    }

    Canvas(modifier = modifier) {
        if (groups.isEmpty() || series.isEmpty()) return@Canvas
        val maxValue = groups.flatMap { it.values }.maxOrNull() ?: 0f
        if (maxValue <= 0f) return@Canvas
        val maxLabelValue = ((maxValue / 10f).toInt() + 1) * 10
        val stepValue = maxLabelValue / steps
        val yAxisLabelWidth = 40.dp.toPx()
        val bottomPadding = 24.dp.toPx()
        val chartAreaWidth = size.width - yAxisLabelWidth
        val chartHeight = size.height - bottomPadding
        val dashPathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 0f), 0f)

        for (i in 0..steps) {
            val value = i * stepValue
            val ratio = value.toFloat() / maxLabelValue.toFloat()
            val y = chartHeight - (ratio * chartHeight)

            val label = "$value $stepSymbol"
            val textLayoutResult = textMeasurer.measure(label, style = textStyle)
            drawText(
                textLayoutResult = textLayoutResult,
                topLeft = Offset(
                    x = yAxisLabelWidth / 2f - textLayoutResult.size.width / 2f,
                    y = y - textLayoutResult.size.height / 2f
                )
            )

            val isZeroLine = value == 0
            val pathEffect = if (isZeroLine) null else dashPathEffect

            drawLine(
                color = axisColor,
                start = Offset(yAxisLabelWidth, y),
                end = Offset(size.width, y),
                strokeWidth = 1.dp.toPx(),
                pathEffect = pathEffect
            )
        }

        val groupWidth = chartAreaWidth / groups.size
        for (i in 0..groups.size) {
            val x = yAxisLabelWidth + i * groupWidth
            drawLine(
                color = Color.Transparent,
                start = Offset(x, 0f),
                end = Offset(x, chartHeight),
                strokeWidth = 1.dp.toPx(),
                pathEffect = dashPathEffect
            )
        }

        val seriesCount = series.size
        val groupInnerPadding = groupWidth * 0.25f
        val availableForBars = groupWidth - groupInnerPadding
        val barWidth = availableForBars / seriesCount

        groups.forEachIndexed { groupIndex, group ->
            val groupStartX = yAxisLabelWidth + groupIndex * groupWidth + groupInnerPadding / 2f

            group.values.forEachIndexed { seriesIndex, value ->
                val ratio = value / maxLabelValue.toFloat()
                val barHeight = ratio * chartHeight
                val x = groupStartX + seriesIndex * barWidth
                val y = chartHeight - barHeight
                val barColor = series[seriesIndex].color
                drawRect(
                    color = barColor,
                    topLeft = Offset(x, y),
                    size = Size(
                        width = barWidth * 0.8f,
                        height = barHeight
                    )
                )
            }

            val label = group.label
            val textLayoutResult = textMeasurer.measure(label, style = textStyle)
            val textX = groupStartX + (groupWidth - groupInnerPadding) / 2f - textLayoutResult.size.width / 2f
            val textY = chartHeight + 4.dp.toPx()
            drawText(
                textLayoutResult = textLayoutResult,
                topLeft = Offset(textX, textY)
            )
        }
    }
}
