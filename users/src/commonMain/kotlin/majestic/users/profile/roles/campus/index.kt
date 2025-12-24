package majestic.users.profile.roles.campus

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.ScreenOrientation
import majestic.users.labels.roles.RolesLabels
import majestic.users.profile.roles.CampusColors
import majestic.users.tools.data.separator

internal enum class CampusMenuAction {
    AddRole, ViewRole, EditRole, DeleteRole
}

@Composable
private fun Modifier.campusItem(
    orientation: ScreenOrientation,
    colors: CampusColors,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
): Modifier {
    val isHovered by interactionSource.collectIsHoveredAsState()
    val separator = colors.theme.surface.contra.color.copy(0.03f)
    val bgColor = if (isHovered) colors.theme.surface.contra.color.copy(alpha = 0.03f) else Color.Transparent

    return this
        .height(80.dp)
        .fillMaxWidth()
        .separator(false, separator)
        .background(bgColor)
        .padding(horizontal = if (orientation is Landscape) 30.dp else 20.dp)
        .hoverable(interactionSource = interactionSource)
}

@Composable
internal fun Campus(
    campusName: String,
    rolesCount: Int,
    labels: RolesLabels,
    colors: CampusColors,
    orientation: ScreenOrientation,
    onAddRole: () -> Unit,
    onViewRole: () -> Unit,
    onEditRole: () -> Unit,
    onDeleteRole: () -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }

    Row(
        modifier = modifier.campusItem(orientation, colors, interactionSource),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)
        ) {
            CampusIcon(colors = colors)
            CampusInfo(
                campusName = campusName,
                rolesCount = rolesCount,
                labels = labels.campus,
                theme = colors.theme
            )
        }

        CampusActions(
            labels = labels,
            colors = colors,
            orientation = orientation,
            onAddRole = onAddRole,
            onViewRole = onViewRole,
            onEditRole = onEditRole,
            onDeleteRole = onDeleteRole
        )
    }
}