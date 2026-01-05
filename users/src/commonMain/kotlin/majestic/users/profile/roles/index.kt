package majestic.users.profile.roles

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import captain.Navigator
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.users.labels.settings.LanguageController
import majestic.users.labels.settings.observeUsersLabels
import majestic.users.profile.roles.assignRoleModalContent.AssignRoleFooter
import majestic.users.profile.roles.assignRoleModalContent.AssignRoleHeader
import majestic.users.profile.roles.campus.CampusMenuAction
import majestic.users.profile.roles.roleItem.RoleItem
import majestic.users.tools.dialogs.Modal

@Composable
fun Roles(
    colors: RolesColors,
    navigator: Navigator,
    language: LanguageController,
    orientation: ScreenOrientation,
    campuses: List<CampusData> = emptyList(),
    roles: List<RoleData> = emptyList()
) {
    val theme = colors.theme
    val labels by observeUsersLabels(language)
    val rolesLabels = labels.profile.roles

    var showAssignRoleModal by remember { mutableStateOf(false) }
    var selectedCampusId by remember { mutableStateOf<String?>(null) }
    var isOpen by remember { mutableStateOf(false) }

    if (showAssignRoleModal) {
        Modal(
            onDismiss = { showAssignRoleModal = false },
            modifier = Modifier
                .fillMaxWidth(if (orientation is Portrait) 0.9f else 0.6f)
                .fillMaxHeight(0.9f),
            colors = colors.dialog
        ) {
            var roleStates by remember {
                mutableStateOf(roles.associate { it.id to it.assignment })
            }

            val selectedCount = roleStates.values.count { it is RoleAssignment.Assigned }

            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    AssignRoleHeader(
                        userName = "Julius Nyerere",
                        labels = rolesLabels.assignModal,
                        colors = colors,
                        modifier = Modifier
                            .fillMaxWidth(),
                    )


                    val isPortrait = orientation == Portrait

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(bottom = if (isPortrait) 140.dp else 80.dp),
                        verticalArrangement = Arrangement.spacedBy(1.dp)
                    ) {
                        roles.forEach { role ->
                            val currentAssignment = roleStates[role.id] ?: RoleAssignment.UnAssigned

                            RoleItem(
                                name = role.name,
                                description = role.description,
                                assignment = currentAssignment,
                                actionType = role.actionType,
                                labels = labels.profile.roles.roleItem,
                                colors = colors.roleItem,
                                orientation = orientation,
                                onPermissionsClick = { /* TODO: Show permissions */ },
                                onClick = {
                                    if (role.actionType == RoleActionType.ASSIGNMENT) {
                                        val newAssignment = when (currentAssignment) {
                                            is RoleAssignment.Assigned -> RoleAssignment.UnAssigned
                                            is RoleAssignment.UnAssigned -> RoleAssignment.Assigned
                                        }

                                        roleStates = roleStates.toMutableMap().apply {
                                            this[role.id] = newAssignment
                                        }
                                    }
                                }
                            )
                        }
                    }
                }

                AssignRoleFooter(
                    selectedCount = selectedCount,
                    labels = rolesLabels.assignModal,
                    orientation = orientation,
                    onDismiss = { showAssignRoleModal = false },
                    onAssign = {
                        val selectedRoleIds = roleStates
                            .filter { it.value is RoleAssignment.Assigned }
                            .keys
                            .toList()

                        showAssignRoleModal = false
                    },
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .background(Color(0xFF1D2430))
                        .padding(16.dp),
                )
            }
        }
    }

    Box(
        contentAlignment = if (orientation is Landscape) {
            Alignment.TopEnd
        } else {
            Alignment.BottomEnd
        }
    ) {
        if (orientation is Landscape) {
            LandscapeAddButton(
                label = rolesLabels.addButton,
                colors = colors.buttonAnimate,
                isOpen = isOpen,
                onToggle = { isOpen = !isOpen },
                modifier = Modifier.offset(x = (-30).dp, y = (-40).dp)
            )
        }

        if (orientation is Portrait) {
            PortraitFab(
                label = rolesLabels.addCampus,
                backgroundColor = colors.background,
                theme = theme,
                flatButtonColors = colors.flatButton,
                isOpen = isOpen,
                onToggle = { isOpen = !isOpen },
                onAddCampus = { /* TODO: Handle add campus */ },
                modifier = Modifier.offset(x = (-30).dp, y = (-40).dp)
            )
        }

        RolesContent(
            campuses = campuses,
            labels = rolesLabels,
            colors = colors,
            orientation = orientation,
            navigator = navigator,
            onCampusAction = { campusId, action ->
                when (action) {
                    CampusMenuAction.AddRole -> {
                        selectedCampusId = campusId
                        showAssignRoleModal = true
                    }

                    CampusMenuAction.ViewRole -> { /* TODO */
                    }

                    CampusMenuAction.EditRole -> { /* TODO */
                    }

                    CampusMenuAction.DeleteRole -> { /* TODO */
                    }
                }
            },
            modifier = if (orientation is Portrait) Modifier.fillMaxSize() else Modifier
        )
    }
}