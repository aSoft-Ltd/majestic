package majestic

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class CircularTickableColors(
    val background: Color,
    val border: Color,
    val icon: ColorPair,
) {
    companion object {
        val Default = CircularTickableColors(
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
fun CircularTickable(
    selected: Boolean,
    icon: ImageVector = Icons.Filled.Check,
    colors: CircularTickableColors = CircularTickableColors.Default,
    size: Dp = 24.dp,
    borderWidth: Dp = 1.dp
) = Box(
    modifier = Modifier
        .size(size)
        .clip(CircleShape)
        .background(colors.background)
        .border(borderWidth, color = colors.border, CircleShape)
) {
    if (selected) {
        Icon(
            modifier = Modifier.fillMaxSize().background(colors.icon.background, CircleShape),
            imageVector = icon,
            tint = colors.icon.foreground,
            contentDescription = "Selected Icon"
        )
    }
}