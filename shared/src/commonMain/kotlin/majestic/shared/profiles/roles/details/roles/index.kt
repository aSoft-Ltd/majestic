package majestic.shared.profiles.roles.details.roles

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.ScreenOrientation
import majestic.editor.toolbar.underline
import majestic.shared.profiles.roles.data.Role
import majestic.shared.profiles.roles.data.RoleData
import majestic.shared.profiles.roles.details.station.StationRolesColors
import majestic.tooling.onClick

@Composable
internal fun RoleList(
    modifier: Modifier,
    orientation: ScreenOrientation,
    station: RoleData,
    labels: RoleActionLabels,
    colors: StationRolesColors,
    onRole: (Role) -> Unit,
    onUnassign: () -> Unit,
) = Column(
    modifier = modifier,
    verticalArrangement = when (orientation) {
        is Landscape -> Arrangement.Top
        else -> Arrangement.spacedBy(6.dp)
    }
) {
    station.roles.forEachIndexed { index, role ->
        RoleItem(
            modifier = Modifier
                .roleItem(
                    orientation = orientation,
                    colors = colors.item,
                    index = index,
                    roles = station.roles
                )
                .onClick { onRole(role) },
            index = index,
            role = role,
            colors = colors.item,
            orientation = orientation,
            labels = labels,
            onView = {
                onRole(role)
            },
            onUnassign = onUnassign
        )
        if (station.roles.lastIndex != station.roles.indexOf(role) && orientation is Landscape) Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .underline(colors.divider, 0.5.dp)
        )
    }
}