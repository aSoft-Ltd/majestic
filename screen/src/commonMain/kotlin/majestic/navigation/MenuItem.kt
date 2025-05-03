package majestic.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import majestic.NoRippleInteractionSource
import majestic.colors.ColorPair

data class MenuItemColors(
    val selected: ColorPair = ColorPair(foreground = Color.White, background = Color.Blue),
    val hovered: ColorPair = ColorPair(foreground = Color.White.copy(alpha = 0.8f), background = Color.Black.copy(alpha = 0.6f)),
    val inactive: ColorPair = ColorPair(foreground = Color.Black, background = Color.White),
    val border: Color = Color.Transparent
) {
    companion object {
        val default by lazy { MenuItemColors() }
    }
}

@Composable
fun MenuItem(
    label: @Composable (style: TextStyle) -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    colors: MenuItemColors = MenuItemColors.default,
    borderWidth: Dp = 0.dp,
    selected: Boolean = false,
    shape: Shape = RoundedCornerShape(10.dp),
    arrangement: Arrangement.Horizontal = Arrangement.Start,
    alignment: Alignment.Vertical = Alignment.CenterVertically,
    trailing: @Composable ((interactionSource: MutableInteractionSource) -> Unit)? = null,
    leading: @Composable ((interactionSource: MutableInteractionSource) -> Unit)? = null
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    val color = when {
        selected -> colors.selected
        isHovered -> colors.hovered
        else -> colors.inactive
    }

    Box(
        modifier = Modifier
            .clip(shape)
            .border(
                width = borderWidth,
                color = colors.border,
                shape = shape
            )
            .background(color = color.background)
            .pointerHoverIcon(PointerIcon.Hand)
            .hoverable(interactionSource = interactionSource)
            .clickable(
                interactionSource = NoRippleInteractionSource,
                indication = null,
                enabled = true,
                onClick = onClick
            ),
    ) {
        Row(
            modifier = modifier,
            horizontalArrangement = arrangement,
            verticalAlignment = alignment,
        ) {
            if (leading != null) {
                leading(interactionSource)
            }
            label(TextStyle(color = color.foreground))
            if (trailing != null) {
                trailing(interactionSource)
            }
        }
    }
}
