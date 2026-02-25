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
import majestic.icons.Res
import majestic.icons.ic_account_setting_filled
import majestic.shared.profiles.roles.assign.FormColors
import majestic.shared.profiles.roles.assign.PromptWrapper
import majestic.shared.profiles.roles.assign.tools.rememberAssignmentController
import majestic.shared.profiles.roles.data.RoleData
import majestic.shared.profiles.roles.data.RoleLabels
import majestic.shared.profiles.roles.data.RoleOption
import majestic.shared.profiles.roles.details.responsibilities.ResponsibilitiesDetail
import majestic.shared.profiles.roles.details.responsibilities.ResponsibilityDetailColors
import majestic.shared.profiles.roles.details.responsibilities.ResponsibilityType
import majestic.shared.profiles.roles.details.station.StationItemColors
import majestic.shared.profiles.roles.details.station.StationList
import majestic.shared.profiles.roles.details.station.StationRoles
import majestic.shared.profiles.roles.details.station.StationRolesColors
import majestic.shared.profiles.roles.tools.Details
import majestic.shared.profiles.roles.tools.Roles
import majestic.shared.profiles.roles.tools.Stations
import majestic.shared.profiles.roles.tools.rememberRoleScreenController
import majestic.shared.tools.breadcrumb.BreadCrumb
import majestic.shared.tools.menu.OptionMenu

data class RoleColors(
    val header: HeaderColors,
    val station: StationItemColors,
    val form: FormColors,
    val separator: Color,
    val roles: StationRolesColors,
    val details: ResponsibilityDetailColors
)

@Composable
fun RoleArea(
    modifier: Modifier = Modifier,
    colors: RoleColors,
    labels: RoleLabels,
    type: ResponsibilityType = ResponsibilityType.NumberResponsibility,
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
                modifier = Modifier.fillMaxWidth(),
                orientation = orientation,
                station = station,
                labels = labels,
                controller = controller,
                colors = colors.roles,
                onRole = { role -> screen.details(role) },
                breadcrumbs = listOf(
                    BreadCrumb(
                        label = labels.roles,
                        icon = station.resource,
                        action = { screen.back() }
                    ),
                    BreadCrumb(
                        label = "${station.station} ${labels.roles}",
                        icon = Res.drawable.ic_account_setting_filled,
                    )
                )
            )
        }

        Details -> screen.activeRole?.let { role ->
            ResponsibilitiesDetail(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(),
                orientation = orientation,
                role = role,
                colors = colors.details,
                type = type,
                breadcrumbs = listOf(
                    BreadCrumb(
                        label = labels.roles,
                        icon = screen.activeRole!!.resource,
                        action = { screen.back() }
                    ),
                    BreadCrumb(
                        label = "${screen.activeStation?.station} ${labels.roles}",
                        icon = Res.drawable.ic_account_setting_filled,
                        action = { screen.back() }
                    ),
                    BreadCrumb(
                        label = role.title,
                        icon = role.resource,
                        action = { screen.back() }
                    )
                )
            )
        }
    }
}