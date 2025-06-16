package majestic

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import majestic.ColorPair

data class IconCheckColors(
    val background: Color,
    val border: Color,
    val icon: ColorPair
) {
    companion object {
        val Default = IconCheckColors(
            background = Color.Transparent,
            border = Color.Transparent,
            icon = ColorPair(
                background = Color.Black,
                foreground = Color.White
            )
        )
    }
}

@Composable
fun IconCheckCircle(
    selected: Boolean,
    colors: IconCheckColors = IconCheckColors.Default,
    size: Dp = 24.dp
) = Box(
    modifier = Modifier
        .size(size)
        .clip(CircleShape)
        .background(colors.icon.background)
        .border(1.dp, colors.border, CircleShape)
) {
    if (selected) {
        Icon(
            modifier = Modifier.padding(4.dp),
            imageVector = Icons.Filled.Check,
            tint = colors.icon.foreground,
            contentDescription = "Selected Icon"
        )
    }
}
