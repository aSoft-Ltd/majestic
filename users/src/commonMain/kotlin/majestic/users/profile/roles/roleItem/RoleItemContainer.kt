package majestic.users.profile.roles.roleItem

import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import majestic.tooling.onClick
import majestic.users.profile.roles.RoleItemColors
import majestic.users.tools.data.separator

@Composable
fun RoleItemContainer(
    colors: RoleItemColors,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val separator = colors.theme.surface.contra.color.copy(0.03f)
    val bgColor = if (isHovered) colors.theme.surface.contra.color.copy(alpha = 0.05f) else colors.background

    Box(
        modifier = modifier
            .separator(false, separator)
            .background(bgColor)
            .padding(20.dp)
            .hoverable(interactionSource = interactionSource)
            .onClick { onClick() }
    ) {
        content()
    }
}