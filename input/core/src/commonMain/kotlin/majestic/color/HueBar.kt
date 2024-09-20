package majestic.color

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import majestic.tooling.clamp

@Composable
fun HueBar(
    modifier: Modifier = Modifier,
    hue: Float = 0f,
    cueSize: Dp = 10.dp,
    onChange: ((hue: Float) -> Unit)? = null
) {
    var state by remember { mutableFloatStateOf(hue) }
    Canvas(
        modifier = modifier.pointerInput(Unit) {
            detectDragGestures { change, _ ->
                state = clamp(0f, (change.position.x * 360) / size.width, 360f)
                onChange?.invoke(state)
            }
        }
    ) {
        val hueY = 0f
        var x = 0f
        val w = 1f
        do {
            val h = (x * 360) / size.width
            val color = Color.hsv(h, 1f, 1f)
            drawLine(color = color, strokeWidth = w, start = Offset(x, hueY), end = Offset(x, size.height))
            x += w
        } while (x < size.width)

        val cueX = (state * size.width) / 360
        val cueY = (size.height + hueY) / 2
        drawCircle(Color.Black.copy(alpha = 0.6f), radius = cueSize.toPx() + 1, center = Offset(cueX, cueY))
        drawCircle(Color.White, radius = cueSize.toPx(), center = Offset(cueX, cueY))
    }
}