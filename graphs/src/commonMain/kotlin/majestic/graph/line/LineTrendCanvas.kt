package majestic.graph.line

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.min

@OptIn(ExperimentalTextApi::class)
@Composable
fun LineTrendCanvas(
    colors: LineTrendChartColors,
    isLandscape: Boolean,
    series: List<LineTrendSeries>,
    xLabels: List<String>,
    minV: Float,
    maxV: Float,
    yTitle: String? = "# of",
    xTitle: String? = "Months",
    modifier: Modifier = Modifier
) = BoxWithConstraints(modifier = modifier) {
    val w = constraints.maxWidth.toFloat()
    val h = constraints.maxHeight.toFloat()
    if (w <= 1f || h <= 1f) return@BoxWithConstraints

    val count = xLabels.size.coerceAtLeast(2)
    val density = LocalDensity.current
    val measurer = rememberTextMeasurer()

    val leftDp = if (!isLandscape) 42.dp else 50.dp
    val rightDp = if (!isLandscape) 20.dp else 30.dp
    val padL = with(density) { min(leftDp.toPx(), w * 0.2f) }
    val padR = with(density) { min(rightDp.toPx(), w * 0.1f) }
    val topDp = if (!isLandscape) 32.dp else 40.dp
    val bottomDp = if (!isLandscape) 56.dp else 50.dp
    val padT = with(density) { min(topDp.toPx(), h * 0.2f) }
    val padB = with(density) { min(bottomDp.toPx(), h * 0.2f) }

    val plotW = (w - padL - padR).coerceAtLeast(1f)
    val plotH = (h - padT - padB).coerceAtLeast(1f)
    val left = padL
    val top = padT
    val right = left + plotW
    val bottom = top + plotH

    fun xAt(idx: Int): Float {
        val t = idx.toFloat() / (count - 1).toFloat()
        return left + plotW * t
    }

    // Y-axis title
    if (yTitle != null) {
        Text(
            text = yTitle,
            color = colors.label,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.offset {
                with(density) {
                    IntOffset(
                        x = (left - 38.dp.toPx()).toInt(),
                        y = (top - 32.dp.toPx()).toInt()
                    )
                }
            }
        )
    }

    // X-axis title
    if (xTitle != null) {
        val xTitleW = remember(xTitle, colors.label) {
            measurer.measure(
                text = xTitle,
                style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Medium)
            ).size.width
        }
        Text(
            text = xTitle,
            color = colors.label,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.offset {
                with(density) {
                    IntOffset(
                        x = (left + plotW / 2f - xTitleW / 2f).toInt(),
                        y = (bottom + 36.dp.toPx()).toInt()
                    )
                }
            }
        )
    }

    Canvas(modifier = Modifier.fillMaxSize()) {
        // Draw Axes
        drawLine(colors.axis, Offset(left, top), Offset(left, bottom), strokeWidth = 1.5.dp.toPx())
        drawLine(colors.axis, Offset(left, bottom), Offset(right, bottom), strokeWidth = 1.5.dp.toPx())

        drawYTicks(measurer, isLandscape, colors.label, left, bottom, plotH, minV, maxV)

        val dash = PathEffect.dashPathEffect(floatArrayOf(4.dp.toPx(), 6.dp.toPx()), 0f)
        for (i in 0 until count) {
            val x = xAt(i)
            drawLine(
                color = colors.grid,
                start = Offset(x, top),
                end = Offset(x, bottom),
                strokeWidth = 1.dp.toPx(),
                pathEffect = dash
            )
        }

        drawXTicks(measurer, xLabels, colors.foreground, colors.label, bottom, ::xAt)

        series.forEach { s ->
            drawTrendLine(s.points, minV, maxV, ::xAt, top, plotH, s.color)
        }
    }
}
