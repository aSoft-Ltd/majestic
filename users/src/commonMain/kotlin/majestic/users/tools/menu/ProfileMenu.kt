package majestic.users.tools.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.ColorPair
import majestic.navigation.MenuItem
import majestic.navigation.MenuItemColors

internal fun Modifier.profileMenu() = fillMaxWidth()
.padding(10.dp)

@Composable
internal fun ProfileMenu(
    title: String,
    subtitle: String,
    image: Painter,
    imageColor: Color,
    modifier: Modifier,
    colors: MenuItemColors = MenuItemColors(
        inactive = ColorPair(foreground = Color.White.copy(alpha = 0.7f), background = Color(0xFF161616)),
        selected = ColorPair(foreground = Color.White, background = Color(0xFF1A3766)),
        hovered = ColorPair(foreground = Color.White, background = Color(0xFF1A3766).copy(alpha = 0.3f))
    ),
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val borderColor = if (isHovered) imageColor else Color.Transparent

    MenuItem(
        modifier = modifier.hoverable(interactionSource),
        label = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(15.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .border(1.dp, borderColor, CircleShape),
                    painter = image,
                    contentDescription = null
                )
                Column {
                    Text(
                        text = title,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = colors.selected.foreground,
                        lineHeight = 1.sp,
                    )
                    Text(
                        text = subtitle,
                        fontSize = 14.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = colors.selected.foreground.copy(.5f),
                        lineHeight = 1.sp,
                    )
                }
            }
        },
        shape = RoundedCornerShape(8.dp),
        colors = colors
    )
}
