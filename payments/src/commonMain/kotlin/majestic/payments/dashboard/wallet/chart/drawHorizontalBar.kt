package majestic.payments.dashboard.wallet.chart

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import majestic.payments.dashboard.wallet.chart.tools.BarShape

internal fun DrawScope.drawHorizontalBar(
    width: Float,
    height: Float,
    value: Float,
    color: Color,
    shape: BarShape,
    animation: Float,
) {
    val barWidth = width * (value / 100) * animation
    drawBarShape(
        shape = shape,
        color = color,
        topLeft = Offset(0f, 0f),
        size = Size(barWidth, height)
    )
}
