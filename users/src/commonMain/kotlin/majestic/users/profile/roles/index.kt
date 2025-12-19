package majestic.users.profile.roles

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.example.demo.roles.*
import composex.screen.orientation.ScreenOrientation

val Campuses = listOf(
    CampusData(campusName = "Sunrise Campus", rolesCount = 25),
    CampusData(campusName = "Riverfront Campus", rolesCount = 33),
    CampusData(campusName = "Mountain View Campus", rolesCount = 17),
    CampusData(campusName = "Oceanfront Campus", rolesCount = 21),
    CampusData(campusName = "Forest Hill Campus", rolesCount = 14),
    CampusData(campusName = "Urban Center", rolesCount = 46)
)

val sampleRoles = listOf(
    Role(
        name = "Subject Teacher",
        description = "Deliver lessons, Assess student performance, Prepare lesson plans, Manage classroom behavior, Communicate with parents about progress",
        assignment = UnAssigned,
        actionType = ActionType.SETUP
    ),
    Role(
        name = "Headmaster",
        description = "Oversee school operations, Implement educational policies, Manage staff, Budget allocation, Handle disciplinary issues, Communicate with parents and stakeholders"
    ),
    Role(
        name = "School Chef",
        description = "Plan and prepare meals, Ensure nutritional standards, Manage kitchen staff, Maintain kitchen hygiene, Budget for food supplies"
    ),
    Role(
        name = "Matron",
        description = "Oversee student welfare, Manage dormitory, Handle student issues, Provide support to students, Liaise with parents"
    ),
    Role(
        name = "Bus Driver",
        description = "Transport students safely, Maintain vehicle cleanliness, Follow designated routes, Ensure student discipline on bus, Report any incidents"
    ),
    Role(
        name = "Librarian",
        description = "Manage library resources, Assist students with research, Organize books and materials, Maintain catalog system, Promote reading culture"
    ),
    Role(
        name = "Security Guard",
        description = "Monitor school premises, Control entry and exit points, Ensure safety of students and staff, Report security incidents, Conduct regular patrols"
    ),
    Role(
        name = "Groundskeeper",
        description = "Maintain school grounds, Manage landscaping, Handle minor repairs, Ensure cleanliness of outdoor areas, Manage waste disposal"
    ),
)

@Composable
fun Roles(orientation: ScreenOrientation, modifier: Modifier = Modifier) {
    var showModal by remember { mutableStateOf(false) }

    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.background(Color(0xFF161616)).safeContentPadding().fillMaxSize()
                .then(if (showModal) Modifier.blur(7.5.dp) else Modifier),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Column(modifier = Modifier.padding(8.dp).background(Color(0xFF191F29))) {
                Campuses.forEach { (campusName, rolesCount) ->
                    Campus(
                        campusName = campusName,
                        rolesCount = rolesCount,
                        onClick = {},
                        onClickMore = {},
                        onClickPlus = { showModal = true },
                        orientation = orientation,
                        modifier = Modifier
                    )
                }
            }
        }

        if (showModal) {
            var totalSelected by remember { mutableStateOf(sampleRoles.filter { it.assignment == Assigned }.size) }

            Modal(
                onDismiss = { showModal = false },
                title = "Assign Roles to Amani Hamduni",
                primaryAction = ModalAction(
                    name = "Assign Selected" + if (totalSelected > 0) " ($totalSelected)" else "",
                    onClick = {},
                    enabled = totalSelected > 0
                ),
                secondaryAction = ModalAction(name = "Cancel", onClick = { showModal = false }),
            ) {

                Column(
                    verticalArrangement = Arrangement.spacedBy(1.dp), modifier = Modifier.fillMaxWidth().verticalScroll(
                        rememberScrollState()
                    )
                ) {
                    sampleRoles.forEachIndexed { index, (name, description, assignment, actionType) ->
                        var isRoleItemHovered by remember { mutableStateOf(false) }
                        var isRoleItemAssigned by remember { mutableStateOf(assignment) }

                        RoleItem(
                            index = index,
                            name = name,
                            description = description,
                            onPermissionsClick = {},
                            onClick = {
                                if (actionType == ActionType.ASSIGNMENT) {
                                    isRoleItemAssigned = when (isRoleItemAssigned) {
                                        is Assigned -> UnAssigned
                                        is UnAssigned -> Assigned
                                    }

                                    if (isRoleItemAssigned == Assigned) totalSelected += 1 else totalSelected -= 1
                                }

                            },
                            isRoleAssignment = isRoleItemAssigned,
                            actionType = actionType,
                            modifier = Modifier.fillMaxWidth().bottomBorder(0.2f, Color.White)
                                .bottomBorder(0.2f, Color.White.copy(alpha = 0.3f))
                                .background(if (isRoleItemHovered) Color(0xFF1E2530) else Color(0xFF191f29))
                                .padding(16.dp).pointerInput(Unit) {
                                    awaitPointerEventScope {
                                        while (true) {
                                            val event = awaitPointerEvent()
                                            when (event.type) {
                                                PointerEventType.Enter -> isRoleItemHovered = true
                                                PointerEventType.Exit -> isRoleItemHovered = false
                                            }
                                        }
                                    }
                                })
                    }
                }
            }
        }
    }
}