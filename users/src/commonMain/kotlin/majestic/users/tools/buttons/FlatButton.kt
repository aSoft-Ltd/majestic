package majestic.users.tools.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import majestic.ColorPair
import majestic.tooling.onClick


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
internal fun FlatButton(
    modifier: Modifier = Modifier,
    colors: FlatButtonColors = FlatButtonColors(),
    onClick: () -> Unit = {},
    content: @Composable (MutableInteractionSource) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val bgColor = if (isHovered) colors.hovered.background else colors.inactive.background

    Box(
        modifier = modifier
            .background(bgColor)
            .pointerHoverIcon(PointerIcon.Hand)
            .hoverable(interactionSource = interactionSource)
            .onClick { onClick() }
            .padding(vertical = 10.dp, horizontal = 20.dp),
        contentAlignment = Alignment.Center
    ) {
        content(interactionSource)
    }
}

@Composable
internal fun FlatButton(
    label: String,
    modifier: Modifier = Modifier,
    colors: FlatButtonColors = FlatButtonColors(),
    onClick: () -> Unit = {}
) = FlatButton(
    modifier = modifier,
    colors = colors,
    onClick = onClick
) {
    val isHovered by it.collectIsHoveredAsState()
    val textColor = if (isHovered) colors.hovered.foreground else colors.inactive.foreground

    Text(text = label, color = textColor, overflow = TextOverflow.Ellipsis, maxLines = 1)
}
