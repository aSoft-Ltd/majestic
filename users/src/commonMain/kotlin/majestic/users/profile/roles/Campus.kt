package majestic.users.profile.roles

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.Landscape
import composex.screen.orientation.ScreenOrientation
import majestic.users.labels.profile.roles.RolesLabels
import majestic.users.tools.data.separator
import majestic.users.tools.menu.MenuOption
import majestic.users.tools.menu.OptionMenu
import org.jetbrains.compose.resources.painterResource
import tz.co.asoft.majestic_users.generated.resources.Res
import tz.co.asoft.majestic_users.generated.resources.ic_more_horizontal
import tz.co.asoft.majestic_users.generated.resources.ic_plus_sign
import tz.co.asoft.majestic_users.generated.resources.ic_square_lock

enum class CampusMenuAction {
    AddRole, ViewRole, EditRole, DeleteRole
}

@Composable
private fun Modifier.campusItem(
    orientation: ScreenOrientation,
    separator: Color,
    colors: CampusColors,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
): Modifier {
    val isHovered by interactionSource.collectIsHoveredAsState()
    val bgColor = if (isHovered) colors.theme.surface.contra.color.copy(alpha = 0.03f) else Color.Transparent
    return height(80.dp).fillMaxWidth().separator(false, separator).background(bgColor)
        .padding(horizontal = if (orientation is Landscape) 30.dp else 20.dp)
        .hoverable(interactionSource = interactionSource)
}

@Composable
fun Campus(
    campusName: String,
    rolesCount: Int,
    labels: RolesLabels,
    colors: CampusColors,
    orientation: ScreenOrientation,
    onAddRole: () -> Unit,
    onViewRole: () -> Unit,
    onEditRole: () -> Unit,
    onDeleteRole: () -> Unit,
    modifier: Modifier = Modifier
) {
    val theme = colors.theme
    val separatorColor = theme.surface.contra.color.copy(0.03f)
    val interactionSource = remember { MutableInteractionSource() }

    Row(
        modifier = modifier.campusItem(orientation, separatorColor, colors, interactionSource),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)
        ) {
            Box(
                modifier = Modifier.size(44.dp).clip(RoundedCornerShape(6.dp)).background(colors.iconBackground)
                    .background(theme.surface.contra.color.copy(alpha = 0.10f)), contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_square_lock),
                    contentDescription = null,
                    tint = colors.iconTint
                )
            }

            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Text(
                    text = campusName,
                    color = theme.surface.contra.color,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 25.2.sp,
                )
                Text(
                    text = "$rolesCount ${if (rolesCount > 1) labels.campus.rolesPlural else labels.campus.rolesSingular}",
                    color = theme.surface.contra.color.copy(alpha = 0.30f),
                    fontSize = 14.sp,
                    fontWeight = FontWeight(450),
                    lineHeight = 19.6.sp,
                )
            }
        }

        Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
            if (orientation is Landscape) {
                val interactionSource = remember { MutableInteractionSource() }
                val isHovered by interactionSource.collectIsHoveredAsState()
                val bgColor = if (isHovered) colors.menuOption.icon.background else Color.Transparent
                val color =
                    if (isHovered) colors.menuOption.icon.foreground else colors.menuOption.icon.foreground.copy(.9f)

                Box(
                    modifier = Modifier.size(32.dp).clip(CircleShape).background(bgColor)
                        .pointerHoverIcon(PointerIcon.Hand).hoverable(interactionSource).clickable { onAddRole() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier.size(20.dp),
                        painter = painterResource(Res.drawable.ic_plus_sign),
                        tint = color,
                        contentDescription = labels.campus.addRoleButton,
                    )
                }
            }

            MenuOption(
                colors = colors.menuOption, orientation = orientation, actions = buildList {
                    if (orientation !is Landscape) {
                        add(OptionMenu(labels.actions.addRole, CampusMenuAction.AddRole))
                    }
                    add(OptionMenu(labels.actions.viewRole, CampusMenuAction.ViewRole))
                    add(OptionMenu(labels.actions.editRole, CampusMenuAction.EditRole))
                    add(OptionMenu(labels.actions.deleteRole, CampusMenuAction.DeleteRole))
                }) { action ->
                when (action) {
                    CampusMenuAction.AddRole -> onAddRole()
                    CampusMenuAction.ViewRole -> onViewRole()
                    CampusMenuAction.EditRole -> onEditRole()
                    CampusMenuAction.DeleteRole -> onDeleteRole()
                }
            }
        }
    }
}