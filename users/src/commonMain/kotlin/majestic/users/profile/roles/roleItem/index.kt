package majestic.users.profile.roles.roleItem

import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.ScreenOrientation
import majestic.tooling.onClick
import majestic.users.labels.roles.RolesLabels
import majestic.users.profile.roles.RoleActionType
import majestic.users.profile.roles.RoleAssignment
import majestic.users.profile.roles.RoleItemColors
import majestic.users.tools.data.separator

@Composable
private fun Modifier.roleItem(
    colors: RoleItemColors, interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
): Modifier {
    val isHovered by interactionSource.collectIsHoveredAsState()
    val separator = colors.theme.surface.contra.color.copy(0.03f)
    val bgColor = if (isHovered) colors.theme.surface.contra.color.copy(alpha = 0.05f) else colors.background

    return this.fillMaxWidth().separator(false, separator).background(bgColor).padding(20.dp)
        .hoverable(interactionSource = interactionSource)
}


@Composable
internal fun RoleItem(
    name: String,
    description: String,
    assignment: RoleAssignment,
    actionType: RoleActionType,
    labels: RolesLabels.RoleItemLabels,
    colors: RoleItemColors,
    onPermissionsClick: () -> Unit,
    onClick: () -> Unit,
    orientation: ScreenOrientation,
    modifier: Modifier = Modifier
) {
    val theme = colors.theme
    val interactionSource = remember { MutableInteractionSource() }

    if (orientation is Landscape) {
        Row(
            modifier = modifier.roleItem(colors, interactionSource).onClick { onClick() },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            RoleItemContent(name, description, actionType, labels, colors, theme)
            RoleItemButton(actionType, assignment, labels, onClick)
        }
    } else {
        Column(
            modifier = modifier.roleItem(colors, interactionSource).onClick { onClick() },
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            RoleItemContent(name, description, actionType, labels, colors, theme)
            Box(
                modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd
            ) {
                RoleItemButton(actionType, assignment, labels, onClick)
            }
        }
    }
}