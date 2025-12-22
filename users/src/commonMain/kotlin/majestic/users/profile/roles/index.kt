package majestic.users.profile.roles

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import captain.Navigator
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.ColorPair
import majestic.ExpandDirection
import majestic.FloatingActionButton
import majestic.floatActionButton
import majestic.tooling.onClick
import majestic.users.labels.settings.LanguageController
import majestic.users.labels.settings.observeUsersLabels
import majestic.users.tools.ProfilePortraitHeader
import majestic.users.tools.buttons.ButtonAnimate
import majestic.users.tools.buttons.FlatButton
import tz.co.asoft.majestic_users.generated.resources.Res
import tz.co.asoft.majestic_users.generated.resources.ic_add

data class CampusData(
    val id: String, val name: String, val rolesCount: Int
)

data class RoleData(
    val id: String,
    val name: String,
    val description: String,
    val assignment: RoleAssignment = RoleAssignment.UnAssigned,
    val actionType: RoleActionType = RoleActionType.ASSIGNMENT
)

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

    var showAssignRoleModal by remember { mutableStateOf(false) }
    var selectedCampusId by remember { mutableStateOf<String?>(null) }

    if (showAssignRoleModal) {
        AssignRoleModal(
            userName = "Amani Hamduni",
            availableRoles = roles,
            colors = colors,
            labels = labels.profile.roles,
            orientation = orientation,
            onDismiss = { showAssignRoleModal = false },
            onAssign = { selectedRoleIds ->
                showAssignRoleModal = false
            })
    }

    Box(contentAlignment = if (orientation is Landscape) Alignment.TopEnd else Alignment.BottomEnd) {
        var isOpen by remember { mutableStateOf(false) }

        if (orientation is Landscape) {
            Box(modifier = Modifier.offset(y = (-40).dp, x = (-30).dp)) {
                ButtonAnimate(
                    label = labels.profile.roles.addButton,
                    icon = Res.drawable.ic_add,
                    isOpen = isOpen,
                    colors = colors.buttonAnimate,
                    onClick = { isOpen = !isOpen })
            }
        }

        if (orientation is Portrait) {
            Box(modifier = Modifier.offset(y = (-40).dp, x = (-30).dp)) {
                FloatingActionButton(
                    modifier = Modifier.floatActionButton(colors.background).onClick { isOpen = !isOpen },
                    direction = ExpandDirection.UP,
                    expanded = isOpen,
                    color = ColorPair(
                        background = theme.surface.contra.color,
                        foreground = theme.surface.actual.color,
                    ),
                    content = {
                        Column(
                            modifier = Modifier.padding(vertical = 10.dp),
                            horizontalAlignment = Alignment.End,
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            FlatButton(
                                colors = colors.flatButton,
                                label = labels.profile.roles.addCampus,
                                onClick = { isOpen = false })
                        }
                    })
            }
        }

        Column(modifier = if (orientation is Portrait) Modifier.fillMaxSize() else Modifier) {
            if (orientation is Portrait) {
                ProfilePortraitHeader(
                    title = labels.profile.roles.heading, colors = colors.profileHeader, navigator = navigator
                )
            }

            Column(
                modifier = Modifier.clip(RoundedCornerShape(20.dp))
                    .background(colors.background)
                    .verticalScroll(rememberScrollState())
                    .then(if (orientation is Landscape) Modifier else Modifier.padding(20.dp)),
                verticalArrangement = if (orientation is Landscape) Arrangement.Top else Arrangement.spacedBy(10.dp)
            ) {
                if (orientation is Landscape) {
                    Text(
                        modifier = Modifier.padding(vertical = 20.dp, horizontal = 30.dp),
                        text = labels.profile.roles.heading,
                        color = theme.surface.contra.color.copy(0.5f),
                    )
                }

                campuses.forEach { campus ->
                    Campus(
                        campusName = campus.name,
                        rolesCount = campus.rolesCount,
                        labels = labels.profile.roles,
                        colors = colors.campus,
                        orientation = orientation,
                        onAddRole = {
                            selectedCampusId = campus.id
                            showAssignRoleModal = true
                        },
                        onViewRole = { /* TODO */ },
                        onEditRole = { /* TODO */ },
                        onDeleteRole = { /* TODO */ })
                }
            }
        }
    }
}