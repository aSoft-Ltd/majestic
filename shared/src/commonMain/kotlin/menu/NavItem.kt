package menu

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.ColorPair
import majestic.navigation.MenuItem
import majestic.navigation.MenuItemColors


internal fun Modifier.navItem(height: Dp = 46.dp) = fillMaxWidth()
    .height(height)
    .padding(vertical = 14.dp, horizontal = 24.dp)

@Composable
internal fun NavItem(
    label: String,
    modifier: Modifier,
    style: TextStyle? = null,
    shape: Shape = RoundedCornerShape(12.dp),
    colors: MenuItemColors = MenuItemColors(
        inactive = ColorPair(foreground = Color.White.copy(alpha = 0.7f), background = Color(0xFF161616)),
        selected = ColorPair(foreground = Color.White, background = Color(0xFF1A3766)),
        hovered = ColorPair(foreground = Color.White, background = Color(0xFF1A3766).copy(alpha = 0.3f))
    ),
    trailing: @Composable ((interactionSource: MutableInteractionSource) -> Unit)? = null,
    leading: @Composable ((interactionSource: MutableInteractionSource) -> Unit)? = null
) {
    MenuItem(
        label = {
            Text(
                text = label,
                style = style ?: it.copy(
                    fontSize = 15.sp
                )
            )
        },
        modifier = modifier,
        shape = shape,
        colors = colors,
        trailing = trailing,
        leading = leading
    )
}

@Composable
internal fun NavItem(
    modifier: Modifier,
    label: String,
    icon: Painter,
    colors: MenuItemColors = MenuItemColors(
        inactive = ColorPair(foreground = Color.White.copy(alpha = 0.7f), background = Color(0xFF161616)),
        selected = ColorPair(foreground = Color.White, background = Color(0xFF1A3766)),
        hovered = ColorPair(foreground = Color.White, background = Color(0xFF1A3766).copy(alpha = 0.3f))
    ),
    trailing: @Composable ((interactionSource: MutableInteractionSource) -> Unit)? = null,
) = NavItem(
    label = label,
    trailing = trailing,
    colors = colors,
    modifier = modifier,
    leading = {
        val isHovered by it.collectIsHoveredAsState()

        Icon(
            modifier = Modifier.padding(start = 10.dp, end = 10.dp),
            painter = icon,
            tint = if (isHovered) colors.hovered.foreground else colors.inactive.foreground,
            contentDescription = "Item Icon"
        )
    }
)
