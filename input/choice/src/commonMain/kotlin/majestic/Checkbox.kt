package majestic

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import majestic.colors.ColorPair

data class CheckboxColors(
    val background: Color,
    val border: Color,
    val icon: ColorPair,
) {
    companion object {
        val Default = CheckboxColors(
            background = Color.Transparent,
            border = Color.Transparent,
            icon = ColorPair(
                background = Color.Black,
                foreground = Color.White
            )
        )
    }
}

fun Modifier.checkbox(colors: CheckboxColors, shape: Shape) = size(24.dp)
    .clip(shape)
    .background(colors.background)
    .border(1.dp, color = colors.border, shape)

@Composable
fun Checkbox(
    selected: Boolean,
    icon: ImageVector = Icons.Filled.Check,
    colors: CheckboxColors = CheckboxColors.Default,
    shape: Shape = RoundedCornerShape(5.dp),
    modifier: Modifier = Modifier.checkbox(colors, shape)
) = Box(modifier = modifier) {
    if (selected) {
        Icon(
            modifier = Modifier.fillMaxSize()
                .background(colors.icon.background, shape)
                .padding(3.dp),
            imageVector = icon,
            tint = colors.icon.foreground,
            contentDescription = "Selected Icon"
        )
    }
}
