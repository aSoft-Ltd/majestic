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

data class MenuColors(
    val color: Color = Color.Black,
    val background: Color = Color.White,
    val selectedColor: Color = Color.White,
    val selectedBackground: Color = Color.Blue,
    val hoverColor: Color = Color.White.copy(alpha = 0.8f),
    val hoverBackground: Color = Color.Black.copy(alpha = 0.6f)
) {
    companion object {
        val default by lazy { MenuColors() }
    }
}

@Composable
fun MenuItem(
    label: @Composable (style: TextStyle) -> Unit,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier,
    colors: MenuColors = MenuColors.default,
    borderColor: Color = Color.Transparent,
    borderWidth: Dp = 0.dp,
    selected: Boolean = false,
    shape: Shape = RoundedCornerShape(10.dp),
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    trailing: @Composable ((interactionSource: MutableInteractionSource) -> Unit)? = null,
    leading: @Composable ((interactionSource: MutableInteractionSource) -> Unit)? = null
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    val labelColor: Color
    val backgroundColor: Color

    if (selected) {
        backgroundColor = colors.selectedBackground
        labelColor = colors.selectedColor
    } else if (isHovered) {
        backgroundColor = colors.hoverBackground
        labelColor = colors.hoverColor
    } else {
        backgroundColor = colors.background
        labelColor = colors.color
    }

    Box(
        modifier = Modifier
            .clip(shape)
            .border(
                width = borderWidth,
                color = borderColor,
                shape = shape
            )
            .background(color = backgroundColor)
            .pointerHoverIcon(PointerIcon.Hand)
            .hoverable(interactionSource = interactionSource)
            .clickable(
                interactionSource = NoRippleInteractionSource(),
                indication = null,
                enabled = true,
                onClick = onSelect
            ),
    ) {
        Row(
            modifier = modifier,
            horizontalArrangement = horizontalArrangement,
            verticalAlignment = verticalAlignment,
        ) {
            if (leading != null) {
                leading(interactionSource)
            }
            label(TextStyle(color = labelColor))
            if (trailing != null) {
                trailing(interactionSource)
            }
        }
    }
}
