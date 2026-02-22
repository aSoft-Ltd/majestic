package majestic.shared.profiles.roles.item

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.editor.toolbar.underline
import majestic.shared.menu.OptionMenu
import majestic.shared.profiles.roles.RoleColors
import majestic.shared.profiles.roles.assign.tools.AssignmentController
import majestic.shared.profiles.roles.data.RoleData
import majestic.shared.profiles.roles.data.RoleLabels
import majestic.shared.profiles.roles.data.RoleOption

@Composable
internal fun StationList(
    modifier: Modifier,
    orientation: ScreenOrientation,
    stations: List<RoleData>,
    colors: RoleColors,
    controller: AssignmentController,
    labels: RoleLabels,
    actions: List<OptionMenu<RoleOption>>,
    onOption: (RoleOption) -> Unit
) = Column(
    modifier = modifier,
    verticalArrangement = when (orientation) {
        is Landscape -> Arrangement.Top
        is Portrait -> Arrangement.spacedBy(4.dp)
    }
) {
    stations.forEach { station ->
        val interaction = remember { MutableInteractionSource() }
        val hovered by interaction.collectIsHoveredAsState()
        RoleItem(
            modifier = Modifier.toRoleItem(
                interaction = interaction,
                orientation = orientation,
                hovered = hovered,
                colors = colors,
                stations = stations,
                campus = station
            ),
            role = station,
            colors = colors.item,
            orientation = orientation,
            onAdd = { controller.open() },
            labels = labels,
            actions = actions,
            onOption = onOption
        )
        if (stations.lastIndex != stations.indexOf(station) && orientation is Landscape) Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .underline(colors.separator, 0.5.dp)
        )
    }
}