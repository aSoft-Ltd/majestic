package majestic.graph.arc

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.Stroke


@Composable
fun GaugeArc(
    progress: Float,
    spec: ArcSpec = ArcSpec(),
    modifier: Modifier = Modifier
) {
    val targetProgress = progress.coerceIn(0f, 1f)
    val animatedProgress by animateFloatAsState(
        targetValue = targetProgress,
        animationSpec = tween(spec.animation.durationMillis),
        label = "gauge-arc-progress"
    )
    val animatedTrack by animateColorAsState(
        targetValue = spec.colors.track,
        animationSpec = tween(spec.animation.durationMillis),
        label = "gauge-arc-track"
    )
    val animatedAccent by animateColorAsState(
        targetValue = spec.colors.progress,
        animationSpec = tween(spec.animation.durationMillis),
        label = "gauge-arc-accent"
    )
    val currentProgress = if (spec.animation.enabled) animatedProgress else targetProgress
    val trackColor = if (spec.animation.enabled) animatedTrack else spec.colors.track
    val progressColor = if (spec.animation.enabled) animatedAccent else spec.colors.progress

    Canvas(modifier = modifier) {
        val strokePx = spec.stroke.width.toPx()
        val stroke = Stroke(width = strokePx, cap = spec.stroke.cap)
        val offset = Offset(spec.layout.offset.x.toPx(), spec.layout.offset.y.toPx())
        val diameter = when (spec.layout.fit) {
            ArcFit.TopHalf -> minOf(size.width - strokePx, size.height * 2f - strokePx)
            ArcFit.Center -> minOf(size.width, size.height) - strokePx
        }.coerceAtLeast(1f)
        val topLeft = when (spec.layout.fit) {
            ArcFit.TopHalf -> Offset(
                x = (size.width - diameter) / 2f,
                y = strokePx / 2f
            )

            ArcFit.Center -> Offset(
                x = (size.width - diameter) / 2f,
                y = (size.height - diameter) / 2f
            )
        } + offset
        val arcSize = Size(diameter, diameter)

        drawArc(
            color = trackColor,
            startAngle = spec.angles.start,
            sweepAngle = spec.angles.sweep,
            useCenter = false,
            topLeft = topLeft,
            size = arcSize,
            style = stroke
        )
        drawArc(
            color = progressColor,
            startAngle = spec.angles.start,
            sweepAngle = spec.angles.sweep * currentProgress,
            useCenter = false,
            topLeft = topLeft,
            size = arcSize,
            style = stroke
        )
    }
}
