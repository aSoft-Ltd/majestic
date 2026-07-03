package majestic.graph.line

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

@OptIn(ExperimentalTextApi::class)
fun DrawScope.drawXTicks(
    measurer: TextMeasurer,
    labels: List<String>,
    fg: Color,
    labelColor: Color,
    bottom: Float,
    xAt: (Int) -> Float
) {
    if (labels.isEmpty()) return
    val maxX = (size.width - 1f).coerceAtLeast(0f)
    labels.forEachIndexed { idx, label ->
        val x = xAt(idx).coerceIn(0f, maxX)
        val isLast = idx == labels.lastIndex
        val labelStyle = TextStyle(
            color = if (isLast) fg.copy(alpha = 0.9f) else labelColor,
            fontSize = 11.sp,
            fontWeight = if (isLast) FontWeight.Bold else FontWeight.Normal
        )
        val textLayout = measurer.measure(AnnotatedString(label), style = labelStyle)
        val labelX = (x - textLayout.size.width / 2f).coerceIn(0f, (maxX - textLayout.size.width).coerceAtLeast(0f))
        val labelY = (bottom + 20.dp.toPx())
        
        drawText(
            textMeasurer = measurer,
            text = AnnotatedString(label),
            topLeft = Offset(labelX, labelY),
            style = labelStyle
        )
        
        if (isLast) {
            drawCircle(
                color = fg,
                radius = 3.dp.toPx(),
                center = Offset(x, labelY + textLayout.size.height + 6.dp.toPx())
            )
        }
    }
}

@OptIn(ExperimentalTextApi::class)
fun DrawScope.drawYTicks(
    measurer: TextMeasurer,
    isLandscape: Boolean,
    labelColor: Color,
    left: Float,
    bottom: Float,
    plotH: Float,
    minV: Float,
    maxV: Float
) {
    val yTicks = if (!isLandscape) 4 else 6
    val maxY = (size.height - 1f).coerceAtLeast(0f)
    val margin = 12.dp.toPx()

    for (i in 0..yTicks) {
        val t = i.toFloat() / yTicks.toFloat()
        val y = (bottom - plotH * t).coerceIn(0f, maxY)
        val vRaw = (minV + (maxV - minV) * t).roundToInt()
        val v = if (vRaw >= 1000) "${vRaw / 1000}K" else vRaw.toString()
        val style = TextStyle(color = labelColor, fontSize = 11.sp)
        val textLayout = measurer.measure(AnnotatedString(v), style = style)
        
        // Calculate X so the text ends 8dp before the axis (left)
        val labelX = (left - textLayout.size.width - margin).coerceAtLeast(0f)
        val labelY = (y - textLayout.size.height / 2f).coerceIn(0f, (maxY - textLayout.size.height).coerceAtLeast(0f))
        
        drawText(
            textMeasurer = measurer,
            text = AnnotatedString(v),
            topLeft = Offset(labelX, labelY),
            style = style
        )
    }
}
