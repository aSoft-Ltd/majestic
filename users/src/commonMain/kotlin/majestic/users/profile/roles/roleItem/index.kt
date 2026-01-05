package majestic.users.profile.roles.roleItem

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.ScreenOrientation
import majestic.users.labels.roles.RoleItemLabels
import majestic.users.labels.roles.RolesLabels
import majestic.users.profile.roles.RoleActionType
import majestic.users.profile.roles.RoleAssignment
import majestic.users.profile.roles.RoleItemColors

@Composable
internal fun RoleItem(
    name: String,
    description: String,
    assignment: RoleAssignment,
    actionType: RoleActionType,
    labels: RoleItemLabels,
    colors: RoleItemColors,
    onPermissionsClick: () -> Unit,
    onClick: () -> Unit,
    orientation: ScreenOrientation,
    modifier: Modifier = Modifier
) {
    val theme = colors.theme

    RoleItemContainer(
        colors = colors, onClick = onClick, modifier = modifier
            .fillMaxWidth()
    ) {
        if (orientation is Landscape) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                RoleItemContent(name, description, actionType, labels, colors, theme)
                RoleItemButton(actionType, assignment, labels, onClick)
            }
        } else {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                RoleItemContent(name, description, actionType, labels, colors, theme)
                RoleItemButton(actionType, assignment, labels, onClick, modifier = Modifier.fillMaxWidth())
            }
        }
    }

}