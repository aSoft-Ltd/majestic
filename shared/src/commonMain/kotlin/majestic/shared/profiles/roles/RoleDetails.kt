package majestic.shared.profiles.roles

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.Landscape
import composex.screen.orientation.ScreenOrientation
import majestic.shared.profiles.Permission
import majestic.shared.profiles.roles.data.Role
import majestic.shared.profiles.roles.data.RoleData
import majestic.shared.profiles.roles.data.RoleLabels
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.vectorResource
import majestic.icons.Res
import majestic.icons.ic_access
import majestic.icons.ic_admission
import majestic.icons.ic_arrow_right
import majestic.icons.ic_user
import majestic.tooling.onClick

internal fun Modifier.roleDetailsContainer(colors: RoleDetailsColors) = this
    .fillMaxWidth()
    .background(colors.background)
    .padding(16.dp)

@Composable
internal fun RoleDetails(
    modifier: Modifier,
    orientation: ScreenOrientation,
    role: Role,
    station: RoleData?,
    labels: RoleLabels,
    colors: RoleDetailsColors,
    onStations: () -> Unit,
    onRoles: () -> Unit
) = Column(
    modifier = modifier.fillMaxHeight(),
    verticalArrangement = Arrangement.spacedBy(16.dp)
) {
    RoleDetailsHeader(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(colors.header.background)
            .padding(if (orientation is Landscape) 20.dp else 10.dp),
        orientation = orientation,
        role = role,
        station = station,
        labels = labels,
        colors = colors.header,
        onStations = onStations,
        onRoles = onRoles
    )

    RoleSummary(
        modifier = Modifier.fillMaxWidth(),
        role = role,
        colors = colors.summary,
        orientation = orientation
    )

    Text(
        text = "Responsibility title",
        color = colors.summary.title,
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold
    )

    RoleResponsibilities(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        responsibilities = role.permissions,
        colors = colors.responsibility,
        orientation = orientation
    )
}

@Composable
private fun RoleDetailsHeader(
    modifier: Modifier,
    orientation: ScreenOrientation,
    role: Role,
    station: RoleData?,
    labels: RoleLabels,
    colors: RoleHeaderColors,
    onStations: () -> Unit,
    onRoles: () -> Unit
) = Row(
    modifier = modifier.horizontalScroll(rememberScrollState()),
    horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
    verticalAlignment = Alignment.CenterVertically
) {
    RoleDetailsTab(
        modifier = Modifier,
        icon = Res.drawable.ic_access,
        label = labels.header,
        active = false,
        colors = colors,
        onClick = onStations
    )

    Icon(
        imageVector = vectorResource(Res.drawable.ic_arrow_right),
        contentDescription = null,
        tint = colors.subtitle,
        modifier = Modifier.size(12.dp)
    )

    RoleDetailsTab(
        modifier = Modifier,
        icon = Res.drawable.ic_admission,
        label = station?.let { "${it.station} ${labels.screens.rolesTitle}" } ?: labels.screens.rolesTitle,
        active = false,
        colors = colors,
        onClick = onRoles
    )

    Icon(
        imageVector = vectorResource(Res.drawable.ic_arrow_right),
        contentDescription = null,
        tint = colors.subtitle,
        modifier = Modifier.size(12.dp)
    )

    RoleDetailsTab(
        modifier = Modifier,
        icon = Res.drawable.ic_user,
        label = role.title,
        active = true,
        colors = colors,
        onClick = null
    )
}

@Composable
private fun RoleDetailsTab(
    modifier: Modifier,
    icon: org.jetbrains.compose.resources.DrawableResource,
    label: String,
    active: Boolean,
    colors: RoleHeaderColors,
    onClick: (() -> Unit)?
) = Row(
    modifier = modifier
        .clip(RoundedCornerShape(10.dp))
        .background(
            colors.background.copy(alpha = if (active) 0.9f else 0.6f)
        )
        .then(
            if (onClick != null) {
                Modifier.pointerHoverIcon(PointerIcon.Hand).onClick { onClick() }
            } else {
                Modifier
            }
        )
        .padding(horizontal = 12.dp, vertical = 8.dp),
    horizontalArrangement = Arrangement.spacedBy(8.dp),
    verticalAlignment = Alignment.CenterVertically
) {
    Icon(
        imageVector = vectorResource(icon),
        contentDescription = null,
        tint = if (active) colors.title else colors.subtitle,
        modifier = Modifier
            .background(
                color = colors.icon.copy(alpha = if (active) 0.2f else 0.12f),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(6.dp)
            .size(18.dp)
    )
    Text(
        text = label,
        color = if (active) colors.title else colors.subtitle,
        fontSize = 14.sp,
        fontWeight = if (active) FontWeight.SemiBold else FontWeight.Medium,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
private fun RoleSummary(
    modifier: Modifier,
    role: Role,
    colors: RoleSummaryColors,
    orientation: ScreenOrientation
) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.Start),
    verticalAlignment = Alignment.CenterVertically
) {
    Icon(
        painter = painterResource(role.resource),
        contentDescription = null,
        tint = colors.icon.foreground,
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(colors.icon.background)
            .padding(10.dp)
            .size(26.dp)
    )

    Column(
        modifier = Modifier.wrapContentSize(),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = role.title,
            color = colors.title,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = role.description,
            color = colors.subtitle,
            fontSize = 13.sp,
            maxLines = if (orientation is Landscape) 3 else 5,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun RoleResponsibilities(
    modifier: Modifier,
    responsibilities: List<Permission>,
    colors: ResponsibilityColors,
    orientation: ScreenOrientation
) = Column(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(8.dp)
) {
    responsibilities.forEachIndexed { index, permission ->
        val interaction = remember { MutableInteractionSource() }
        val hovered by interaction.collectIsHoveredAsState()
        ResponsibilityItem(
            modifier = Modifier.responsibilityItem(
                interaction = interaction,
                hovered = hovered,
                orientation = orientation,
                colors = colors
            ),
            index = index,
            permission = permission,
            colors = colors
        )
    }
}

private fun Modifier.responsibilityItem(
    interaction: MutableInteractionSource,
    hovered: Boolean,
    orientation: ScreenOrientation,
    colors: ResponsibilityColors
) = this
    .fillMaxWidth()
    .hoverable(interaction)
    .background(
        color = if (hovered) colors.background.focused else colors.background.unfocused,
        shape = RoundedCornerShape(10.dp)
    )
    .padding(if (orientation is Landscape) 20.dp else 10.dp)

@Composable
private fun ResponsibilityItem(
    modifier: Modifier,
    index: Int,
    permission: Permission,
    colors: ResponsibilityColors
) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
    verticalAlignment = Alignment.CenterVertically
) {
    Icon(
        painter = painterResource(permission.resource),
        contentDescription = null,
        tint = colors.icon.foreground,
        modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .background(colors.icon.background)
            .padding(8.dp)
            .size(24.dp)
    )

    Column(
        modifier = Modifier.wrapContentSize(),
        verticalArrangement = Arrangement.spacedBy(2.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "${index + 1}. ${permission.title}",
            color = colors.title,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = permission.description,
            color = colors.subtitle,
            fontSize = 12.sp,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )
    }
}
