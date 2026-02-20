package majestic.shared.profiles.roles

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import composex.screen.orientation.ScreenOrientation
import majestic.shared.profiles.roles.data.Role
import majestic.shared.profiles.roles.data.RoleData
import majestic.shared.profiles.roles.data.RoleScreenLabels
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
    labels: RoleScreenLabels,
    colors: StationRolesColors,
    onBack: () -> Unit,
    onRole: (Role) -> Unit
) = Column(
    modifier = modifier
        .stationRolesContainer(colors),
    verticalArrangement = Arrangement.spacedBy(16.dp)
) {
    RolesHeader(
        modifier = Modifier.roleScreenHeader(colors.header, orientation),
        title = station.station,
        subtitle = labels.rolesTitle,
        colors = colors.header,
        onBack = onBack
    )

    RoleList(
        modifier = Modifier.fillMaxWidth(),
        orientation = orientation,
        station = station,
        colors = colors,
        onRole = onRole
    )
}
