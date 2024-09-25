package majestic

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import majestic.tooling.clamp


/**
 * ----------------
 * |              |
 * |              |
 * |              | (80)
 * |              |
 * ----------------
 * */
@Composable
fun SVColorCoordinate(
    modifier: Modifier = Modifier,
    hue: Float = 0f,
    saturation: Float = 0f,
    value: Float = 0f,
    cellSize: Float = 10f, // Warning values below 5 will cause a problem in android
    cueSize: Dp = 10.dp,
    onChange: ((saturation: Float, value: Float) -> Unit)? = null
) = Column(modifier = modifier) {

    var saturationState by remember { mutableFloatStateOf(saturation) }
    var valueState by remember { mutableFloatStateOf(value) }

    Canvas(
        modifier = Modifier.fillMaxSize()
            .pointerInput(Unit) {
                detectDragGestures { change, _ ->
                    val it = change.position
                    saturationState = clamp(0f, it.x, size.width.toFloat())
                    valueState = clamp(0f, it.y, size.height.toFloat())
                    val s = saturationState / size.width
                    val v = valueState / size.height
                    onChange?.invoke(s, 1f - v)
                }
            }
    ) {
        var cx = 0f
        var cy = 0f
        while (cx < size.width) {
            val s = cx / size.width
            while (cy < size.height) {
                val v = 1f - (cy / size.height)
                val c = Color.hsv(hue, s, v)
                drawRect(color = c, topLeft = Offset(cx, cy), size = Size(cellSize, cellSize))
                cy += cellSize
            }
            cy = 0f
            cx += cellSize
        }
        val sv = Offset(saturationState, valueState)
        drawCircle(Color.Black.copy(alpha = 0.6f), radius = cueSize.toPx() + 1, center = sv)
        drawCircle(Color.White, radius = cueSize.toPx(), center = sv)
    }
}