package majestic.shared.profiles.roles.details.station

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
import majestic.shared.profiles.roles.RoleColors
import majestic.shared.profiles.roles.assign.tools.AssignmentController
import majestic.shared.profiles.roles.data.RoleData
import majestic.shared.profiles.roles.data.RoleLabels
import majestic.shared.profiles.roles.data.RoleOption
import majestic.shared.tools.menu.OptionMenu
import majestic.shared.tools.rememberHoverBackground

@Composable
internal fun StationList(
    modifier: Modifier,
    orientation: ScreenOrientation,
    stations: List<RoleData>,
    colors: RoleColors,
    controller: AssignmentController,
    labels: RoleLabels,
    actions: List<OptionMenu<RoleOption>>,
    onOption: (RoleOption) -> Unit,
    onStation: (RoleData) -> Unit
) = Column(
    modifier = modifier,
    verticalArrangement = Arrangement.Top
) {
    stations.forEach { station ->
        val (background, interaction) = rememberHoverBackground(
            background = colors.station.background,
            foreground = colors.station.foreground
        )
        StationItem(
            modifier = Modifier.stationItem(
                interaction = interaction,
                background = background,
                orientation = orientation,
                stations = stations,
                campus = station
            ),
            station = station,
            colors = colors.station,
            orientation = orientation,
            onStation = { onStation(station) },
            onAdd = { controller.open() },
            labels = labels,
            actions = actions,
            onOption = onOption
        )
        if (stations.lastIndex != stations.indexOf(station)) Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .underline(colors.separator, if (orientation is Landscape) 0.5.dp else 1.dp)
        )
    }
}