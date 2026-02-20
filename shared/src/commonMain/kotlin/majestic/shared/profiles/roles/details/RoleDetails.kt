
package majestic.shared.profiles.roles.details

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
import majestic.shared.profiles.tools.BreadCrumbTab
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
        modifier = Modifier.fillMaxWidth(),
        role = role,
        station = station,
        labels = labels,
        colors = colors.header,
        onStations = onStations,
        onRoles = onRoles
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
    BreadCrumbTab(
        modifier = Modifier.onClick { onStations() },
        icon = Res.drawable.ic_access,
        label = labels.header,
        colors = colors.breadCrumb,
        showLabel = true
    )

    Icon(
        imageVector = vectorResource(Res.drawable.ic_arrow_right),
        contentDescription = null,
        tint = colors.subtitle,
        modifier = Modifier.size(12.dp)
    )

    BreadCrumbTab(
        modifier = Modifier.onClick { onRoles() },
        icon = Res.drawable.ic_admission,
        label = station?.let { "${it.station} ${labels.screens.rolesTitle}" } ?: labels.screens.rolesTitle,
        colors = colors.breadCrumb,
        showLabel = true
    )

    Icon(
        imageVector = vectorResource(Res.drawable.ic_arrow_right),
        contentDescription = null,
        tint = colors.subtitle,
        modifier = Modifier.size(12.dp)
    )

    BreadCrumbTab(
        modifier = Modifier,
        icon = Res.drawable.ic_user,
        label = role.title,
        colors = colors.breadCrumb,
        showLabel = true
    )
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
    .padding(20.dp)

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

    Column(
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
