package majestic.shared.profiles.roles.details.roles

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.ScreenOrientation
import majestic.editor.toolbar.underline
import majestic.shared.profiles.roles.data.Role
import majestic.shared.profiles.roles.data.RoleData
import majestic.shared.profiles.roles.details.station.StationRolesColors

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
            modifier = Modifier.roleItem(
                orientation = orientation,
                colors = colors.item,
                index = index,
                roles = station.roles
            ),
            index = index,
            role = role,
            colors = colors.item,
            orientation = orientation,
            onClick = { onRole(role) },
            labels = labels,
            onUnassign = onUnassign
        )
        if (station.roles.lastIndex != station.roles.indexOf(role) && orientation is Landscape) Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .underline(colors.divider, 0.5.dp)
        )
    }
}
