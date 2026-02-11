package majestic

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * A rotating loader component that shows an animated circular progress with
 * a fixed-length arc that rotates continuously.
 *
 * @param modifier Modifier to be applied to the component
 * @param size The size of the component
 * @param strokeWidth Width of the stroke for the circular progress
 * @param arcLength Length of the arc in degrees (out of 360)
 * @param color ColorPair for the background and foreground colors
 * @param durationMillis Duration of one complete rotation in milliseconds
 * @param content Optional composable content to display in the center
 */
@Composable
fun CircularLoader(
    modifier: Modifier = Modifier,
    strokeWidth: Dp = 8.dp,
    arcLength: Float = 45f,
    color: ColorPair = ColorPair(
        background = Color.Black.copy(alpha = 0.2f),
        foreground = Color.White
    ),
    durationMillis: Int = 1500,
    content: @Composable (BoxScope.() -> Unit)? = null
) {
    val arcPercentage = arcLength / 360f

    val infiniteTransition = rememberInfiniteTransition()
    val startAngle by infiniteTransition.animateFloat(
        initialValue = -90f,
        targetValue = 270f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        content?.invoke(this)
        CircularProgress(
            modifier = Modifier.matchParentSize(),
            percentage = arcPercentage,
            color = color,
            stroke = strokeWidth,
            startAngle = startAngle
        )

    }
}