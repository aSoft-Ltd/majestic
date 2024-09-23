package majestic.color.format

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.util.fastRoundToInt

fun Color.toCssHex(): String = buildString {
    append("#")
    append((red * 255).fastRoundToInt().toString(16).padStart(2, '0'))
    append((green * 255).fastRoundToInt().toString(16).padStart(2, '0'))
    append((blue * 255).fastRoundToInt().toString(16).padStart(2, '0'))
}

fun Color.Companion.fromHex(colorString: String) = Color(
    colorString.removePrefix("#").toLong(16) or 0x00000000FF000000
)
