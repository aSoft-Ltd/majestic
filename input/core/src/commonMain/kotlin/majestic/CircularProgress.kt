package majestic

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import majestic.colors.ColorPair
import majestic.tooling.animateAsState
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun CircularProgress(
    modifier: Modifier = Modifier,
    percentage: Float,
    color: ColorPair = ColorPair(
        background = Color.Black.copy(alpha = 0.2f),
        foreground = Color.White
    ),
    stroke: Dp = 8.dp,
    startAngle: Float = -90f
) = Canvas(modifier = modifier) {
    val radius = size.minDimension / 2
    val line = stroke.toPx()
    val rect = Size(radius * 2 - line, radius * 2 - line)
    val point = Offset((size.width - rect.width) / 2, (size.height - rect.height) / 2)
    drawCircle(
        color = color.background,
        center = Offset(size.width / 2, size.height / 2),
        radius = radius - line / 2,
        style = Stroke(line, cap = StrokeCap.Round)
    )
    drawArc(
        color = color.foreground,
        startAngle = startAngle,
        sweepAngle = 360f * percentage,
        topLeft = point,
        useCenter = false,
        size = rect,
        style = Stroke(line, cap = StrokeCap.Round)
    )
}
