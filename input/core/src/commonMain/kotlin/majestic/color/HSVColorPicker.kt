package majestic.color

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import majestic.color.format.toCssHex


/**
 * ----------------
 * |              |
 * |              |
 * |              | (80)
 * |              |
 * ----------------
 *                  (5)
 * ----------------
 * |              | (15)
 * ----------------
 */
//@Composable
//fun HSVColorPicker(
//    modifier: Modifier = Modifier,
//    cueSize: Dp = 10.dp,
//) = Column(modifier = modifier) {
//
//    var hueState by remember { mutableFloatStateOf(0f) }
//
//    var saturation by remember { mutableFloatStateOf(0f) }
//    var value by remember { mutableFloatStateOf(0f) }
//
//    val boxPct = 0.75f
//    val gapPct = 0.10f
//    val cell = 10f
//
//    Canvas(
//        modifier = Modifier.fillMaxSize()
//            .pointerInput(Unit) {
//                detectDragGestures { change, _ ->
//                    val it = change.position
//                    if (it.y < size.height * boxPct) {
//                        saturation = it.x
//                        value = it.y
//                    } else if (it.y > size.height * (boxPct + gapPct)) {
//                        // deal with hue bar later
//                        val hue = (it.x * 360) / size.width
//                        hueState = when {
//                            hue < 0f -> 0f
//                            hue > 360f -> 360f
//                            else -> hue
//                        }
//                    }
//                }
//            }
//    ) {
//        val box = Size(width = size.width, height = size.height * boxPct)
//        var cx = 0f
//        var cy = 0f
//        while (cx < box.width) {
//            val s = cx / box.width
//            while (cy < box.height) {
//                val v = 1f - (cy / box.height)
//                val c = Color.hsv(hueState, s, v)
//                drawRect(color = c, topLeft = Offset(cx, cy), size = Size(cell, cell))
//                cy += cell
//            }
//            cy = 0f
//            cx += cell
//        }
//
//        val sv = Offset(saturation, value)
//        println("SV(${sv.x},${sv.y})")
//        drawCircle(Color.Black.copy(alpha = 0.6f), radius = cueSize.toPx() + 1, center = sv)
//        drawCircle(Color.White, radius = cueSize.toPx(), center = sv)
//
//        val hueY = size.height * (boxPct + gapPct)
//        // Hue bar
//        var x = 0f
//        val w = 1f
//        do {
//            val hue = (x * 360) / size.width
//            val c = Color.hsv(hue, 1f, 1f)
//            drawLine(color = c, strokeWidth = w, start = Offset(x, hueY), end = Offset(x, size.height))
//            x += w
//        } while (x < size.width)
//
//        val cueX = (hueState * size.width) / 360
//        val cueY = (size.height + hueY) / 2
//        drawCircle(Color.Black.copy(alpha = 0.6f), radius = cueSize.toPx() + 1, center = Offset(cueX, cueY))
//        drawCircle(Color.White, radius = cueSize.toPx(), center = Offset(cueX, cueY))
//    }
//}

@Composable
fun HSVColorPicker(
    modifier: Modifier = Modifier,
    hue: Float = 0f,
    saturation: Float = 0f,
    value: Float = 0f,
    cueSize: Dp = 10.dp,
    onChange: ((h: Float, s: Float, v: Float) -> Unit)? = null
) = Column(modifier = modifier) {
    var h by remember { mutableFloatStateOf(hue) }
    var s by remember { mutableFloatStateOf(saturation) }
    var v by remember { mutableFloatStateOf(value) }

    val color by remember(h, s, v) {
        derivedStateOf {
            println("h: $h, s: $s, v: $v")
            Color.hsv(h, s, v)
        }
    }

    SVColorCoordinate(
        modifier = Modifier.fillMaxWidth().height(400.dp),
        hue = h,
        saturation = saturation,
        value = value,
        cueSize = cueSize,
        onChange = { sat, va ->
            s = sat
            v = va
            onChange?.invoke(h, sat, va)
        }
    )

    Spacer(modifier = Modifier.height(50.dp))

    HueBar(
        modifier = Modifier.fillMaxWidth().height(100.dp),
        hue = h,
        cueSize = cueSize,
        onChange = {
            h = it
            onChange?.invoke(h, s, v)
        }
    )

    Text(color.toCssHex())
}