package majestic.users.profile.roles

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.ColorPair
import majestic.users.labels.profile.roles.RolesLabels
import majestic.users.tools.buttons.FlatButton
import majestic.users.tools.buttons.FlatButtonColors
import majestic.users.tools.dialogs.Modal
import org.jetbrains.compose.resources.painterResource
import tz.co.asoft.majestic_users.generated.resources.Res
import tz.co.asoft.majestic_users.generated.resources.ic_account_settings_filled

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
    val isPortrait = orientation == Portrait

    Modal(
        theme = colors.theme,
        background = colors.background,
        orientation = orientation,
        onDismiss = onDismiss,
        modifier = Modifier.fillMaxWidth(if (orientation is Portrait) 0.9f else 0.6f)
            .fillMaxHeight(0.9f)
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Header - fixed at top
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
                    .background(Color(0xFF202733))
                    .padding(20.dp),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_account_settings_filled),
                        contentDescription = "Account Settings Filled",
                        tint = Color(0xFFD18C27),
                        modifier = Modifier.clip(RoundedCornerShape(6.dp))
                            .background(Color(0xFF000000).copy(alpha = 0.10f))
                            .background(Color(0xFF1D2430))
                            .padding(12.dp)
                            .size(24.dp)
                    )

                    Text(
                        text = "${labels.assignModal.title} $userName",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = colors.theme.surface.contra.color
                    )
                }
            }

            // Roles list - takes remaining space, scrollable
            Box(
                modifier = Modifier
                    .weight(1f) // Takes all available space
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(bottom = if (isPortrait) 140.dp else 80.dp), // Space for footer
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
            }
        }

        // Footer - positioned at bottom using Box
        val isEnabled = selectedCount > 0

        // Button content lambda to avoid duplication
        val buttons: @Composable (Modifier) -> Unit = { buttonModifier ->
            val roundedModifier = buttonModifier.clip(CircleShape)

            FlatButton(
                modifier = roundedModifier,
                label = labels.assignModal.cancel,
                onClick = onDismiss,
                colors = FlatButtonColors(
                    hovered = ColorPair(
                        foreground = Color.White,
                        background = Color.White.copy(alpha = 0.30f)
                    ),
                    inactive = ColorPair(
                        foreground = Color.White,
                        background = Color.White.copy(alpha = 0.20f)
                    )
                )
            )

            FlatButton(
                modifier = roundedModifier,
                label = if (isEnabled)
                    "${labels.assignModal.assignSelected} ($selectedCount)"
                else
                    labels.assignModal.assignSelected,
                onClick = {
                    if (isEnabled) {
                        val selectedRoleIds = roleStates
                            .filter { it.value is RoleAssignment.Assigned }
                            .keys
                            .toList()
                        onAssign(selectedRoleIds)
                    }
                },
                colors = FlatButtonColors(
                    hovered = ColorPair(
                        foreground = Color(0xCC15181D),
                        background = Color.White.copy(alpha = 0.9f)
                    ),
                    inactive = ColorPair(
                        foreground = if (isEnabled) Color(0xCC15181D) else Color(0xCC15181D).copy(alpha = 0.60f),
                        background = if (isEnabled) Color.White else Color.White.copy(alpha = 0.5f)
                    )
                )
            )
        }

        // Use Column for portrait, Row for landscape
        if (isPortrait) {
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .background(Color(0xFF1D2430))
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                buttons(Modifier.fillMaxWidth())
            }
        } else {
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .background(Color(0xFF1D2430))
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.End),
                verticalAlignment = Alignment.CenterVertically
            ) {
                buttons(Modifier)
            }
        }

    }
}