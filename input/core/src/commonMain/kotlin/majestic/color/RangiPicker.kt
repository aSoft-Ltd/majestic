package majestic.color

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import majestic.color.format.ColorPickerFormat
import majestic.color.format.HEX
import majestic.color.format.HSL
import majestic.color.format.HSV
import majestic.color.format.RGB

@Composable
fun RangiPicker(
    format: ColorPickerFormat = HSV
) {
    when (format) {
        HEX -> {}
        HSL -> HueBar(
            modifier = Modifier.height(100.dp).fillMaxWidth().clip(RoundedCornerShape(50.dp))
        )

        HSV -> {
            HSVColorPicker(
                modifier = Modifier.fillMaxSize()
            )
        }

        RGB -> {}
    }
}