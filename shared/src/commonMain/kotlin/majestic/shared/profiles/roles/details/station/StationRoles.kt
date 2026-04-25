package majestic.shared.profiles.roles.details.station

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.ColorPair
import majestic.button.appearence.plusButton
import majestic.button.basic.PlusButton
import majestic.shared.profiles.roles.assign.tools.AssignmentController
import majestic.shared.profiles.roles.data.Role
import majestic.shared.profiles.roles.data.RoleData
import majestic.shared.profiles.roles.data.RoleLabels
import majestic.shared.profiles.roles.details.roles.RoleHeaderColors
import majestic.shared.profiles.roles.details.roles.RoleItemColors
import majestic.shared.profiles.roles.details.roles.RoleList
import majestic.shared.profiles.roles.details.roles.RolesHeader
import majestic.shared.profiles.roles.details.roles.roleScreenHeader
import majestic.shared.tools.breadcrumb.BreadCrumb

data class StationRolesColors(
    val background: Color,
    val header: RoleHeaderColors,
    val item: RoleItemColors,
    val divider: Color,
    val iconButon: ColorPair,
)

@Composable
internal fun StationRoles(
    modifier: Modifier,
    orientation: ScreenOrientation,
    station: RoleData,
    labels: RoleLabels,
    colors: StationRolesColors,
    breadcrumbs: List<BreadCrumb>,
    onRole: (Role) -> Unit,
    controller: AssignmentController
) = Box(modifier = modifier) {
    Column(
        modifier = when (orientation) {
            is Landscape -> Modifier.wrapContentSize()
            is Portrait -> Modifier.fillMaxSize()
        },
        verticalArrangement = Arrangement.Top
    ) {
        RolesHeader(
            modifier = Modifier.roleScreenHeader(orientation = orientation),
            orientation = orientation,
            count = station.roles.size,
            breadcrumbs = breadcrumbs,
            colors = colors.header,
            controller = controller,
            labels = labels.details
        )

        RoleList(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            orientation = orientation,
            station = station,
            colors = colors,
            onRole = onRole,
            labels = labels.action,
            onUnassign = {}
        )
    }
    if (orientation is Portrait) PlusButton(
        modifier = Modifier
            .padding(20.dp)
            .align(Alignment.BottomEnd)
            .plusButton(
                colors = colors.iconButon,
                onClick = { controller.open() },
            )
    )
}