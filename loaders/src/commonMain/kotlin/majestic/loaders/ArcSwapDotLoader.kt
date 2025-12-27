package majestic.loaders

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import kotlinx.coroutines.delay
import kotlin.math.PI
import kotlin.math.sin

private fun arcXYConstHeight(
    x0: Float,
    x1: Float,
    t: Float,
    baseY: Float,
    vertR: Float
): Offset {
    val x = lerp(x0, x1, t)
    val y = baseY - (vertR * sin(PI * t.toDouble())).toFloat()
    return Offset(x, y)
}

@Composable
fun ArcSwapDotLoader(
    modifier: Modifier = Modifier,
    dotColor: Color = Color(0xFF0D0D0D),
    dotSize: Dp = 5.dp,
    gap: Dp = 4.dp,
    durationMillis: Int = 1000,
    arcHeightMultiplier: Float = 1.0f,
    pauseMillis: Long = 1000
) {
    val widthDp = dotSize * 3 + gap * 2
    val stepDp = dotSize + gap
    val density = LocalDensity.current

    val radiusPx = remember(dotSize, density) { with(density) { (dotSize / 2).toPx() } }
    val stepPx = remember(stepDp, density) { with(density) { stepDp.toPx() } }
    val vertR = remember(stepPx, arcHeightMultiplier) { stepPx * arcHeightMultiplier }
    val heightDp = remember(dotSize, vertR, density) { dotSize + with(density) { vertR.toDp() } }
    val heightPx = remember(heightDp, density) { with(density) { heightDp.toPx() } }
    val slotX = remember(radiusPx, stepPx) {
        floatArrayOf(
            radiusPx,
            radiusPx + stepPx,
            radiusPx + 2 * stepPx
        )
    }
    val baseY = remember(heightPx, radiusPx) { heightPx - radiusPx }
    var slotToDot by remember { mutableStateOf(intArrayOf(0, 1, 2)) }
    val p = remember { Animatable(0f) }

    LaunchedEffect(pauseMillis, durationMillis, arcHeightMultiplier, dotSize, gap) {
        while (true) {
            p.animateTo(1f, animationSpec = tween(durationMillis, easing = LinearEasing))
            delay(pauseMillis)
            p.snapTo(0f)
            slotToDot = intArrayOf(slotToDot[1], slotToDot[2], slotToDot[0])
        }
    }

    Canvas(modifier = modifier.width(widthDp).height(heightDp)) {
        for (dotId in 0..2) {
            val startSlot = slotToDot.indexOf(dotId)
            val (x0, x1, isArc) = when (startSlot) {
                0 -> Triple(slotX[0], slotX[2], true)
                1 -> Triple(slotX[1], slotX[0], false)
                else -> Triple(slotX[2], slotX[1], false)
            }
            val pos = if (isArc) arcXYConstHeight(x0 = x0, x1 = x1, t = p.value, baseY = baseY, vertR = vertR)
            else Offset(lerp(x0, x1, p.value), baseY)
            drawCircle(color = dotColor, radius = radiusPx, center = pos)
        }
    }
}