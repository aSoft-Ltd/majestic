package majestic.users.profile.roles.campus

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import majestic.users.labels.roles.RolesLabels
import majestic.users.profile.roles.CampusColors
import org.jetbrains.compose.resources.painterResource
import tz.co.asoft.majestic_users.generated.resources.Res
import tz.co.asoft.majestic_users.generated.resources.ic_plus_sign

@Composable
fun CampusAddButton(
    labels: RolesLabels.CampusLabels,
    colors: CampusColors,
    onAddRole: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val bgColor = if (isHovered) colors.menuOption.icon.background else Color.Transparent
    val color = if (isHovered) colors.menuOption.icon.foreground
    else colors.menuOption.icon.foreground.copy(.9f)

    Box(
        modifier = modifier
            .size(32.dp)
            .clip(CircleShape)
            .background(bgColor)
            .pointerHoverIcon(PointerIcon.Hand)
            .hoverable(interactionSource)
            .clickable { onAddRole() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.size(20.dp),
            painter = painterResource(Res.drawable.ic_plus_sign),
            tint = color,
            contentDescription = labels.addRoleButton,
        )
    }
}