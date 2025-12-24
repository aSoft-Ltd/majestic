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
import majestic.users.labels.roles.RolesLabels
import majestic.users.labels.settings.LanguageController
import majestic.users.labels.settings.observeUsersLabels
import majestic.users.profile.roles.assignRoleModalContent.AssignRoleModalContent
import majestic.users.profile.roles.campus.Campus
import majestic.users.profile.roles.campus.CampusMenuAction
import majestic.users.tools.ProfilePortraitHeader
import majestic.users.tools.buttons.ButtonAnimate
import majestic.users.tools.buttons.FlatButton
import majestic.users.tools.dialogs.Modal
import tz.co.asoft.majestic_users.generated.resources.Res
import tz.co.asoft.majestic_users.generated.resources.ic_add

@Composable
private fun RolesHeader(
    title: String,
    colors: RolesColors,
    orientation: ScreenOrientation
) {
    if (orientation is Landscape) {
        Text(
            modifier = Modifier.padding(vertical = 20.dp, horizontal = 30.dp),
            text = title,
            color = colors.theme.surface.contra.color.copy(0.5f),
        )
    }
}

@Composable
private fun CampusesList(
    campuses: List<CampusData>,
    labels: RolesLabels,
    colors: RolesColors,
    orientation: ScreenOrientation,
    onCampusAction: (String, CampusMenuAction) -> Unit
) {
    campuses.forEach { campus ->
        Campus(
            campusName = campus.name,
            rolesCount = campus.rolesCount,
            labels = labels,
            colors = colors.campus,
            orientation = orientation,
            onAddRole = { onCampusAction(campus.id, CampusMenuAction.AddRole) },
            onViewRole = { onCampusAction(campus.id, CampusMenuAction.ViewRole) },
            onEditRole = { onCampusAction(campus.id, CampusMenuAction.EditRole) },
            onDeleteRole = { onCampusAction(campus.id, CampusMenuAction.DeleteRole) }
        )
    }
}

@Composable
private fun LandscapeAddButton(
    label: String,
    colors: majestic.users.tools.buttons.ButtonAnimateColors,
    isOpen: Boolean,
    onToggle: () -> Unit
) {
    Box(modifier = Modifier.offset(y = (-40).dp, x = (-30).dp)) {
        ButtonAnimate(
            label = label,
            icon = Res.drawable.ic_add,
            isOpen = isOpen,
            colors = colors,
            onClick = onToggle
        )
    }
}

@Composable
private fun PortraitFab(
    label: String,
    backgroundColor: androidx.compose.ui.graphics.Color,
    theme: majestic.ThemeColor,
    flatButtonColors: majestic.users.tools.buttons.FlatButtonColors,
    isOpen: Boolean,
    onToggle: () -> Unit,
    onAddCampus: () -> Unit
) {
    Box(modifier = Modifier.offset(y = (-40).dp, x = (-30).dp)) {
        FloatingActionButton(
            modifier = Modifier
                .floatActionButton(backgroundColor)
                .onClick { onToggle() },
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
                        colors = flatButtonColors,
                        label = label,
                        onClick = {
                            onToggle()
                            onAddCampus()
                        }
                    )
                }
            }
        )
    }
}

@Composable
private fun RolesContent(
    campuses: List<CampusData>,
    labels: RolesLabels,
    colors: RolesColors,
    orientation: ScreenOrientation,
    navigator: Navigator,
    onCampusAction: (String, CampusMenuAction) -> Unit
) {
    Column(
        modifier = if (orientation is Portrait) Modifier.fillMaxSize() else Modifier
    ) {
        if (orientation is Portrait) {
            ProfilePortraitHeader(
                title = labels.heading,
                colors = colors.profileHeader,
                navigator = navigator
            )
        }

        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(colors.background)
                .verticalScroll(rememberScrollState())
                .then(
                    if (orientation is Landscape) Modifier
                    else Modifier.padding(20.dp)
                ),
            verticalArrangement = if (orientation is Landscape) {
                Arrangement.Top
            } else {
                Arrangement.spacedBy(10.dp)
            }
        ) {
            RolesHeader(
                title = labels.heading,
                colors = colors,
                orientation = orientation
            )

            CampusesList(
                campuses = campuses,
                labels = labels,
                colors = colors,
                orientation = orientation,
                onCampusAction = onCampusAction
            )
        }
    }
}

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
            theme = colors.theme,
            background = colors.background,
            orientation = orientation,
            onDismiss = { showAssignRoleModal = false },
            modifier = Modifier
                .fillMaxWidth(if (orientation is Portrait) 0.9f else 0.6f)
                .fillMaxHeight(0.9f)
        ) {
            AssignRoleModalContent(
                userName = "Amani Hamduni",
                availableRoles = roles,
                colors = colors,
                rolesLabels = rolesLabels,
                orientation = orientation,
                onDismiss = { showAssignRoleModal = false },
                onAssign = { selectedRoleIds ->
                    // TODO: Handle role assignment
                    showAssignRoleModal = false
                }
            )
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
                onToggle = { isOpen = !isOpen }
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
                onAddCampus = { /* TODO: Handle add campus */ }
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
                    CampusMenuAction.ViewRole -> { /* TODO */ }
                    CampusMenuAction.EditRole -> { /* TODO */ }
                    CampusMenuAction.DeleteRole -> { /* TODO */ }
                }
            }
        )
    }
}