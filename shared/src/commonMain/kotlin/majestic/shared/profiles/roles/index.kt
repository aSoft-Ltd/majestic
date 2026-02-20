package majestic.shared.profiles.roles

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.shared.menu.OptionMenu
import majestic.shared.profiles.roles.assign.FormColors
import majestic.shared.profiles.roles.assign.PromptWrapper
import majestic.shared.profiles.roles.assign.tools.rememberAssignmentController
import majestic.shared.profiles.roles.data.RoleData
import majestic.shared.profiles.roles.data.RoleLabels
import majestic.shared.profiles.roles.data.RoleOption
import majestic.shared.profiles.roles.item.StationItemColors
import majestic.shared.profiles.roles.item.StationList

data class RoleColors(
    val header: HeaderColors,
    val station: StationItemColors,
    val form: FormColors,
    val separator: Color,
    val roles: StationRolesColors,
    val details: RoleDetailsColors
)

@Composable
fun RoleArea(
    modifier: Modifier = Modifier,
    colors: RoleColors,
    labels: RoleLabels,
    actions: List<OptionMenu<RoleOption>>,
    orientation: ScreenOrientation,
    stations: List<RoleData>,
    username: String,
    onOption: (RoleOption) -> Unit
) = Column(modifier = modifier) {

    val (assignedStations, _) = stations.partition { it.roles.isNotEmpty() }

    val controller = rememberAssignmentController(
        userName = username,
        total = stations,
        assigned = assignedStations
    )
    PromptWrapper(controller, colors, orientation, labels)

    val screen = rememberRoleScreenController()

    when (screen.view) {
        Stations -> {
            Header(
                modifier = Modifier.toHeader(orientation, colors),
                colors = colors.header,
                orientation = orientation,
                header = labels.header
            )
            StationList(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .then(
                        when (orientation) {
                            is Portrait -> Modifier.fillMaxWidth()
                            is Landscape -> Modifier.wrapContentSize()
                        }
                    ),
                orientation = orientation,
                stations = stations,
                colors = colors,
                controller = controller,
                labels = labels,
                actions = actions,
                onOption = onOption,
                onStation = { station -> screen.roles(station) }
            )
        }

        Roles -> screen.activeStation?.let { station ->
            StationRoles(
                modifier = Modifier,
                orientation = orientation,
                station = station,
                labels = labels.screens,
                controller = controller,
                colors = colors.roles,
                onBack = { screen.back() },
                onRole = { role -> screen.details(role) }
            )
        }

        Details -> screen.activeRole?.let { role ->
            RoleDetails(
                modifier = Modifier
                    .roleDetailsContainer(colors.details),
                orientation = orientation,
                role = role,
                station = screen.activeStation,
                labels = labels,
                colors = colors.details,
                onStations = { screen.stations() },
                onRoles = { screen.activeStation?.let { station -> screen.roles(station) } }
            )
        }
    }
}
