package majestic.users.profile.roles

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import composex.screen.orientation.Landscape
import composex.screen.orientation.ScreenOrientation
import majestic.ColorPair
import majestic.ThemeColor
import majestic.tooling.onClick
import majestic.users.labels.profile.roles.RolesLabels
import majestic.users.tools.buttons.FlatButton
import majestic.users.tools.buttons.FlatButtonColors
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
    return fillMaxWidth().separator(false, separator).background(bgColor).padding(20.dp)
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
    orientation: ScreenOrientation,
    modifier: Modifier = Modifier
) {
    val theme = colors.theme
    val separatorColor = theme.surface.contra.color.copy(0.03f)
    val interactionSource = remember { MutableInteractionSource() }

    if (orientation is Landscape) {
        Row(
            modifier = modifier.roleItem(separatorColor, colors, interactionSource).onClick { onClick() },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            RoleItemContent(name, description, actionType, labels, colors, theme)
            RoleItemButton(actionType, assignment, labels, onClick)
        }
    } else {
        Column(
            modifier = modifier.roleItem(separatorColor, colors, interactionSource).onClick { onClick() },
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            RoleItemContent(name, description, actionType, labels, colors, theme)
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                RoleItemButton(actionType, assignment, labels, onClick)
            }
        }
    }
}

@Composable
private fun RoleItemContent(
    name: String,
    description: String,
    actionType: RoleActionType,
    labels: RolesLabels.RoleItemLabels,
    colors: RoleItemColors,
    theme: ThemeColor
) {
    Column(
        modifier = Modifier.widthIn(min = 300.dp), verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.CenterVertically
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
                    modifier = Modifier.clip(CircleShape).background(colors.setupBadgeBackground)
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
}

@Composable
private fun RoleItemButton(
    actionType: RoleActionType, assignment: RoleAssignment, labels: RolesLabels.RoleItemLabels, onClick: () -> Unit
) {
    FlatButton(
        modifier = Modifier.clip(CircleShape).width(200.dp).then(
            if (actionType == RoleActionType.SETUP) Modifier.border(1.dp, Color.White.copy(0.5f), CircleShape)
            else Modifier
        ), label = when (actionType) {
            RoleActionType.SETUP -> labels.setupAction
            RoleActionType.ASSIGNMENT -> if (assignment is RoleAssignment.Assigned) "Unassign" else "Assign"
        }, colors = when {
            actionType == RoleActionType.SETUP -> FlatButtonColors(
                hovered = ColorPair(
                    foreground = Color.White, background = Color.White.copy(alpha = 0.15f)
                ),
                inactive = ColorPair(
                    foreground = Color.White, background = Color.White.copy(alpha = 0.10f)
                ),
            )

            assignment is RoleAssignment.Assigned -> FlatButtonColors(
                hovered = ColorPair(
                    foreground = Color(0xFFEF5350), background = Color(0xFFEF5350).copy(alpha = 0.15f)
                ),
                inactive = ColorPair(
                    foreground = Color(0xFFEF5350).copy(alpha = 0.7f),
                    background = Color(0xFFEF5350).copy(alpha = 0.07f)
                ),
            )

            else -> FlatButtonColors(
                hovered = ColorPair(
                    foreground = Color.White, background = Color.Black.copy(alpha = 0.25f)
                ),
                inactive = ColorPair(
                    foreground = Color.White, background = Color.Black.copy(alpha = 0.2f)
                ),
            )
        }, onClick = onClick
    )
}