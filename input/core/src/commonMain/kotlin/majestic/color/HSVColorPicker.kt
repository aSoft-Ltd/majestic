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
