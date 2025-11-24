package majestic.payments.tools.menu

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
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.navigation.MenuItem
import majestic.navigation.MenuItemColors
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

internal fun Modifier.navItem(height: Dp = 46.dp) = fillMaxWidth()
    .height(height)
    .padding(vertical = 14.dp, horizontal = 24.dp)

@Composable
internal fun NavItem(
    label: String,
    colors: MenuItemColors,
    shape: Shape = RoundedCornerShape(12.dp),
    style: TextStyle? = null,
    modifier: Modifier = Modifier,
    trailing: @Composable ((interactionSource: MutableInteractionSource) -> Unit)? = null,
    leading: @Composable ((interactionSource: MutableInteractionSource) -> Unit)? = null
) {
    MenuItem(
        label = {
            Text(
                text = label,
                style = style ?: it.copy(fontSize = 15.sp)
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
    label: String,
    icon: DrawableResource,
    colors: MenuItemColors,
    modifier: Modifier = Modifier,
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
            painter = painterResource(icon),
            tint = if (isHovered) colors.hovered.foreground else colors.inactive.foreground,
            contentDescription = "Item Icon"
        )
    }
)
