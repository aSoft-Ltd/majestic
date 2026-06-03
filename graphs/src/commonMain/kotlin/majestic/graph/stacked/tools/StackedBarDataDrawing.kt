package majestic.graph.stacked.tools

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.DrawScope
import majestic.graph.stacked.StackedBarChartConfig
import majestic.graph.stacked.utils.StackedChartLayoutInfo

internal fun DrawScope.drawStackedBars(
    config: StackedBarChartConfig,
    layout: StackedChartLayoutInfo,
    animationProgress: Float,
    yRange: Float
) {
    val dataSize = config.data.size
    if (dataSize <= 0) return

    val slotWidth = layout.chartWidth / dataSize
    val barWidth = slotWidth * 0.5f
    val barOffset = (slotWidth - barWidth) / 2f

    config.data.forEachIndexed { index, item ->
        val slotX = layout.chartLeft + index * slotWidth
        val barX = slotX + barOffset
        
        var currentY = layout.chartBottom
        item.values.forEachIndexed { vIndex, value ->
            val barHeight = (value / yRange) * layout.chartHeight * animationProgress
            val barY = currentY - barHeight
            
            val color = config.colors.stacks.getOrElse(vIndex) { config.colors.stacks.last() }
            
            drawRect(
                color = color,
                topLeft = Offset(barX, barY),
                size = Size(barWidth, barHeight)
            )
            currentY = barY
        }
    }
}
