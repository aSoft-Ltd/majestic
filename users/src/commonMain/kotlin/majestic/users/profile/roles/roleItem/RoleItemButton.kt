package majestic.users.profile.roles.roleItem

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import majestic.ColorPair
import majestic.users.labels.roles.RolesLabels
import majestic.users.profile.roles.RoleActionType
import majestic.users.profile.roles.RoleAssignment
import majestic.users.tools.buttons.FlatButton
import majestic.users.tools.buttons.FlatButtonColors

@Composable
fun RoleItemButton(
    actionType: RoleActionType,
    assignment: RoleAssignment,
    labels: RolesLabels.RoleItemLabels,
    onClick: () -> Unit
) {
    val buttonLabel = when (actionType) {
        RoleActionType.SETUP -> labels.setupAction
        RoleActionType.ASSIGNMENT -> when (assignment) {
            is RoleAssignment.Assigned -> labels.unassignAction
            is RoleAssignment.UnAssigned -> labels.assignAction
        }
    }

    val buttonColors = when {
        actionType == RoleActionType.SETUP -> FlatButtonColors(
            hovered = ColorPair(
                foreground = Color.White,
                background = Color.White.copy(alpha = 0.15f)
            ),
            inactive = ColorPair(
                foreground = Color.White,
                background = Color.White.copy(alpha = 0.10f)
            )
        )

        assignment is RoleAssignment.Assigned -> FlatButtonColors(
            hovered = ColorPair(
                foreground = Color(0xFFEF5350),
                background = Color(0xFFEF5350).copy(alpha = 0.15f)
            ),
            inactive = ColorPair(
                foreground = Color(0xFFEF5350).copy(alpha = 0.7f),
                background = Color(0xFFEF5350).copy(alpha = 0.07f)
            )
        )

        else -> FlatButtonColors(
            hovered = ColorPair(
                foreground = Color.White,
                background = Color.Black.copy(alpha = 0.25f)
            ),
            inactive = ColorPair(
                foreground = Color.White,
                background = Color.Black.copy(alpha = 0.2f)
            )
        )
    }

    val buttonModifier = Modifier
        .clip(CircleShape)
        .width(200.dp)
        .then(
            if (actionType == RoleActionType.SETUP) {
                Modifier.border(1.dp, Color.White.copy(0.5f), CircleShape)
            } else Modifier
        )

    FlatButton(
        modifier = buttonModifier,
        label = buttonLabel,
        colors = buttonColors,
        onClick = onClick
    )
}