package majestic.loaders

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

fun Modifier.animateDots(background: Color) = this
    .size(28.dp)
    .clip(RoundedCornerShape(8.dp))
    .background(background)
    .padding(6.dp)

@Composable
fun AnimatedDots(
    color: Color,
    duration: Int,
    delay: Long,
    size: Dp,
    modifier: Modifier = Modifier
) {
    var dot1Alpha by remember { mutableStateOf(0.5f) }
    var dot2Alpha by remember { mutableStateOf(0.5f) }
    var dot3Alpha by remember { mutableStateOf(0.5f) }

    LaunchedEffect(Unit) {
        while (true) {
            dot1Alpha = 1f
            delay(delay)
            dot2Alpha = 1f
            delay(delay)
            dot3Alpha = 1f
            dot1Alpha = 0.5f
            delay(delay)
            dot2Alpha = 0.5f
            dot3Alpha = 0.5f
        }
    }

    val dot1 by animateColorAsState(
        targetValue = color.copy(dot1Alpha),
        animationSpec = tween(durationMillis = duration)
    )
    val dot2 by animateColorAsState(
        targetValue = color.copy(dot2Alpha),
        animationSpec = tween(durationMillis = duration)
    )
    val dot3 by animateColorAsState(
        targetValue = color.copy(dot3Alpha),
        animationSpec = tween(durationMillis = duration)
    )

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(modifier = Modifier.size(size).background(dot1, CircleShape))
            Box(modifier = Modifier.size(size).background(dot2, CircleShape))
            Box(modifier = Modifier.size(size).background(dot3, CircleShape))
        }
    }
}
