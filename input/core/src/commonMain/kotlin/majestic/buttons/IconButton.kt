package majestic.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import majestic.ColorPair
import majestic.tooling.onClick

data class IconButtonColors(
    val hovered: ColorPair,
    val inactive: ColorPair
) {
    companion object {
        val default by lazy {
            IconButtonColors(
                hovered = ColorPair(background = Color.Black, foreground = Color.White),
                inactive = ColorPair(background = Color.Black.copy(0.2f), foreground = Color.White.copy(0.7f))
            )
        }
    }
}


@Deprecated("Use Button composable, it has option to pass icon only")
@Composable
fun IconButton(
    colors: IconButtonColors = IconButtonColors.default,
    icon: Painter,
    size: Dp = 32.dp,
    shape: Shape = CircleShape,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val color = if (isHovered) colors.hovered else colors.inactive

    Box(
        modifier = Modifier
            .background(color.background, shape)
            .hoverable(interactionSource = interactionSource)
            .onClick { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.size(size).padding(6.dp),
            painter = icon,
            tint = color.foreground,
            contentDescription = "Icon"
        )
    }
}

@Deprecated("Use Button composable, it has option to pass icon only")
@Composable
fun IconButton(
    modifier: Modifier = Modifier,
    onHover: (Boolean) -> Unit = {},
    content: @Composable () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    onHover(isHovered)

    Box(
        modifier = modifier.hoverable(interactionSource = interactionSource),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}
