package majestic.loaders

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke

@Composable
fun UploadProgressIndicator(
    progress: Float,
    gradient: List<Color>,
    track: Color,
    foreground: Color,
    modifier: Modifier = Modifier
) {
    val displayedProgress by animateFloatAsState(
        targetValue = progress.coerceIn(0f, 1f),
        animationSpec = tween(durationMillis = 150, easing = LinearEasing),
        label = "uploadProgress"
    )
    Canvas(modifier = modifier) {
        val diameter = size.minDimension * 0.8f
        val stroke = diameter / 16f
        val radius = (diameter - stroke) / 2f
        val topLeft = Offset(center.x - radius, center.y - radius)
        val arcSize = Size(radius * 2, radius * 2)
        val brush = Brush.horizontalGradient(gradient)
        if (displayedProgress >= 0.999f) {
            drawCircle(brush, radius)
            val check = Path().apply {
                moveTo(center.x - radius * 0.3f, center.y)
                lineTo(center.x - radius * 0.1f, center.y + radius * 0.3f)
                lineTo(center.x + radius * 0.3f, center.y - radius * 0.3f)
            }
            drawPath(check, foreground, style = Stroke(stroke, cap = StrokeCap.Round))
        }
        else {
            drawCircle(track, radius, style = Stroke(stroke))
            drawArc(
                brush = brush,
                startAngle = -90f,
                sweepAngle = displayedProgress * 360f,
                useCenter = false,
                topLeft = topLeft,
                size = arcSize,
                style = Stroke(stroke, cap = StrokeCap.Round)
            )
            val cross = radius * 0.3f
            drawLine(
                color = foreground,
                start = center - Offset(cross, cross),
                end = center + Offset(cross, cross),
                strokeWidth = stroke,
                cap = StrokeCap.Round
            )
            drawLine(
                color = foreground,
                start = center + Offset(-cross, cross),
                end = center + Offset(cross, -cross),
                strokeWidth = stroke,
                cap = StrokeCap.Round
            )
        }
    }
}