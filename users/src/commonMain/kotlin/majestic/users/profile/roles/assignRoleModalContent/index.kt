package majestic.users.profile.roles.assignRoleModalContent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import composex.screen.orientation.ScreenOrientation
import majestic.users.labels.roles.RolesLabels
import majestic.users.profile.roles.RoleAssignment
import majestic.users.profile.roles.RoleData
import majestic.users.profile.roles.RolesColors


@Composable
internal fun AssignRoleModalContent(
    userName: String,
    availableRoles: List<RoleData>,
    colors: RolesColors,
    rolesLabels: RolesLabels,
    orientation: ScreenOrientation,
    onDismiss: () -> Unit,
    onAssign: (List<String>) -> Unit,
    modifier: Modifier = Modifier
) {
    var roleStates by remember {
        mutableStateOf(availableRoles.associate { it.id to it.assignment })
    }

    val selectedCount = roleStates.values.count { it is RoleAssignment.Assigned }

    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            AssignRoleHeader(
                userName = userName,
                labels = rolesLabels.assignModal,
                colors = colors
            )

            RolesList(
                availableRoles = availableRoles,
                roleStates = roleStates,
                labels = rolesLabels,
                colors = colors,
                orientation = orientation,
                onRoleStateChange = { roleId, newAssignment ->
                    roleStates = roleStates.toMutableMap().apply {
                        this[roleId] = newAssignment
                    }
                },
                modifier = Modifier.weight(1f)
            )
        }

        AssignRoleFooter(
            selectedCount = selectedCount,
            labels = rolesLabels.assignModal,
            orientation = orientation,
            onDismiss = onDismiss,
            onAssign = {
                val selectedRoleIds = roleStates
                    .filter { it.value is RoleAssignment.Assigned }
                    .keys
                    .toList()
                onAssign(selectedRoleIds)
            },
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}