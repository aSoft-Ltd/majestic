package majestic.users.profile.roles.assignRoleModalContent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.users.labels.roles.RolesLabels
import majestic.users.profile.roles.RoleActionType
import majestic.users.profile.roles.RoleAssignment
import majestic.users.profile.roles.RoleData
import majestic.users.profile.roles.RolesColors
import majestic.users.profile.roles.roleItem.RoleItem

@Composable
fun RolesList(
    availableRoles: List<RoleData>,
    roleStates: Map<String, RoleAssignment>,
    labels: RolesLabels,
    colors: RolesColors,
    orientation: ScreenOrientation,
    onRoleStateChange: (String, RoleAssignment) -> Unit,
    modifier: Modifier = Modifier
) {
    val isPortrait = orientation == Portrait

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(bottom = if (isPortrait) 140.dp else 80.dp),
        verticalArrangement = Arrangement.spacedBy(1.dp)
    ) {
        availableRoles.forEach { role ->
            val currentAssignment = roleStates[role.id] ?: RoleAssignment.UnAssigned

            RoleItem(
                name = role.name,
                description = role.description,
                assignment = currentAssignment,
                actionType = role.actionType,
                labels = labels.roleItem,
                colors = colors.roleItem,
                orientation = orientation,
                onPermissionsClick = { /* TODO: Show permissions */ },
                onClick = {
                    if (role.actionType == RoleActionType.ASSIGNMENT) {
                        val newAssignment = when (currentAssignment) {
                            is RoleAssignment.Assigned -> RoleAssignment.UnAssigned
                            is RoleAssignment.UnAssigned -> RoleAssignment.Assigned
                        }
                        onRoleStateChange(role.id, newAssignment)
                    }
                }
            )
        }
    }
}