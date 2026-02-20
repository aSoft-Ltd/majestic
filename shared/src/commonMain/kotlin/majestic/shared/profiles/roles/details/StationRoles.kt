package majestic.shared.profiles.roles.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import composex.screen.orientation.ScreenOrientation
import majestic.shared.profiles.roles.assign.tools.AssignmentController
import majestic.shared.profiles.roles.data.Role
import majestic.shared.profiles.roles.data.RoleData
import majestic.shared.profiles.roles.data.RoleLabels
import majestic.shared.profiles.roles.item.RoleList

internal fun Modifier.stationRolesContainer(colors: StationRolesColors) = this
    .fillMaxWidth()
    .background(colors.background)
    .padding(16.dp)

@Composable
internal fun StationRoles(
    modifier: Modifier,
    orientation: ScreenOrientation,
    station: RoleData,
    labels: RoleLabels,
    colors: StationRolesColors,
    onBack: () -> Unit,
    onRole: (Role) -> Unit,
    controller: AssignmentController
) = Column(
    modifier = modifier
        .stationRolesContainer(colors),
    verticalArrangement = Arrangement.spacedBy(16.dp)
) {
    RolesHeader(
        modifier = Modifier.roleScreenHeader(colors.header, orientation),
        orientation = orientation,
        count = station.roles.size,
        searchLabel = "Search Roles", // Or find in labels
        rolesLabel = labels.screens.rolesTitle,
        breadcrumbs = breadcrumbs,
        colors = colors.header,
        controller = controller
    )

    RoleList(
        modifier = Modifier.fillMaxWidth(),
        orientation = orientation,
        station = station,
        colors = colors,
        onRole = onRole
    )
}
