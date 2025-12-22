package majestic.users.profile.roles

import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.tooling.onClick
import majestic.users.labels.profile.roles.RolesLabels
import majestic.users.tools.data.separator


sealed class RoleAssignment {
    object Assigned : RoleAssignment()
    object UnAssigned : RoleAssignment()
}

enum class RoleActionType {
    SETUP, ASSIGNMENT
}

@Composable
private fun Modifier.roleItem(
    separator: Color,
    colors: RoleItemColors,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
): Modifier {
    val isHovered by interactionSource.collectIsHoveredAsState()
    val bgColor = if (isHovered) colors.theme.surface.contra.color.copy(alpha = 0.05f) else colors.background
    return fillMaxWidth()
        .separator(false, separator)
        .background(bgColor)
        .padding(16.dp)
        .hoverable(interactionSource = interactionSource)
}

@Composable
fun RoleItem(
    name: String,
    description: String,
    assignment: RoleAssignment,
    actionType: RoleActionType,
    labels: RolesLabels.RoleItemLabels,
    colors: RoleItemColors,
    onPermissionsClick: () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val theme = colors.theme
    val separatorColor = theme.surface.contra.color.copy(0.03f)
    val interactionSource = remember { MutableInteractionSource() }

    Row(
        modifier = modifier.roleItem(separatorColor, colors, interactionSource).onClick { onClick() },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = name,
                    color = theme.surface.contra.color,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 22.4.sp
                )

                if (actionType == RoleActionType.SETUP) {
                    Text(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(colors.setupBadgeBackground)
                            .padding(horizontal = 8.dp, vertical = 2.dp),
                        text = labels.setupBadge,
                        fontSize = 12.sp,
                        lineHeight = 16.8.sp,
                        color = colors.setupBadgeText,
                    )
                }
            }

            Text(
                text = description,
                color = theme.surface.contra.color.copy(alpha = 0.50f),
                fontSize = 14.sp,
                lineHeight = 19.6.sp
            )
        }

        when (actionType) {
            RoleActionType.SETUP -> {
                Text(
                    text = labels.setupAction,
                    color = theme.surface.contra.color,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.onClick { onClick() }
                )
            }

            RoleActionType.ASSIGNMENT -> {
                Text(
                    text = if (assignment is RoleAssignment.Assigned) "Unassign" else "Assign",
                    color = if (assignment is RoleAssignment.Assigned)
                        theme.surface.contra.color
                    else
                        theme.surface.contra.color.copy(0.5f),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}
