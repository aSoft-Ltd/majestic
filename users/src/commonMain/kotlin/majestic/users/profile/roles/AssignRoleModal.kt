package majestic.users.profile.roles

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.ScreenOrientation
import majestic.users.labels.profile.roles.RolesLabels
import majestic.users.tools.buttons.FlatButton
import majestic.users.tools.dialogs.Modal

@Composable
fun AssignRoleModal(
    userName: String,
    availableRoles: List<RoleData>,
    colors: RolesColors,
    labels: RolesLabels,
    orientation: ScreenOrientation,
    onDismiss: () -> Unit,
    onAssign: (List<String>) -> Unit,
) {
    var roleStates by remember {
        mutableStateOf(availableRoles.associate { it.id to it.assignment })
    }

    val selectedCount = roleStates.values.count { it is RoleAssignment.Assigned }

    Modal(
        theme = colors.theme,
        background = colors.background,
        orientation = orientation,
        onDismiss = onDismiss
    ) {
        Column(
            modifier = Modifier.padding(vertical = 40.dp, horizontal = 30.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Header
            Text(
                text = "${labels.assignModal.title} $userName",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = colors.theme.surface.contra.color
            )

            // Roles list
            Column(
                modifier = Modifier
                    .weight(1f, fill = false)
                    .verticalScroll(rememberScrollState()),
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
                        onPermissionsClick = { /* TODO: Show permissions */ },
                        onClick = {
                            if (role.actionType == RoleActionType.ASSIGNMENT) {
                                roleStates = roleStates.toMutableMap().apply {
                                    this[role.id] = when (currentAssignment) {
                                        is RoleAssignment.Assigned -> RoleAssignment.UnAssigned
                                        is RoleAssignment.UnAssigned -> RoleAssignment.Assigned
                                    }
                                }
                            }
                        }
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                FlatButton(
                    modifier = Modifier.weight(1f),
                    label = labels.assignModal.cancel,
                    colors = colors.flatButton,
                    onClick = onDismiss
                )
                FlatButton(
                    modifier = Modifier.weight(1f),
                    label = if (selectedCount > 0)
                        "${labels.assignModal.assignSelected} ($selectedCount)"
                    else
                        labels.assignModal.assignSelected,
                    colors = colors.flatButton,
                    onClick = {
                        if (selectedCount > 0) {
                            val selectedRoleIds = roleStates
                                .filter { it.value is RoleAssignment.Assigned }
                                .keys
                                .toList()
                            onAssign(selectedRoleIds)
                        }
                    }
                )
            }
        }
    }
}