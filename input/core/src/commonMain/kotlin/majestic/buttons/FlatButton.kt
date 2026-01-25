package majestic.buttons

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import majestic.ColorPair


data class FlatButtonColors(
    val hovered: ColorPair = ColorPair(
        foreground = Color.White,
        background = Color.Black.copy(alpha = 0.5f)
    ),
    val inactive: ColorPair = ColorPair(
        foreground = Color.White.copy(alpha = 0.8f),
        background = Color.Black.copy(alpha = 0.2f)
    ),
)

@Composable
fun FlatButton(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) = Box(
    modifier = modifier,
    contentAlignment = Alignment.Center
) {
    content()
}

@Composable
fun FlatButton(
    label: String,
    modifier: Modifier = Modifier,
    colors: FlatButtonColors = FlatButtonColors(),
    interactionSource: MutableInteractionSource
) = FlatButton(
    modifier = modifier,
) {
    val isHovered by interactionSource.collectIsHoveredAsState()
    val textColor = if (isHovered) colors.hovered.foreground else colors.inactive.foreground

    Text(text = label, color = textColor, overflow = TextOverflow.Ellipsis, maxLines = 1)
}
