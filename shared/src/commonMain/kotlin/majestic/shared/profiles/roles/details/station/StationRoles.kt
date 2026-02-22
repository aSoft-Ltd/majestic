package majestic.shared.profiles.roles.details.station

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.icons.Res
import majestic.icons.ic_add
import majestic.shared.profiles.roles.assign.form.ActionColors
import majestic.shared.profiles.roles.assign.tools.AssignmentController
import majestic.shared.profiles.roles.data.Role
import majestic.shared.profiles.roles.data.RoleData
import majestic.shared.profiles.roles.data.RoleLabels
import majestic.shared.profiles.roles.details.roles.RoleHeaderColors
import majestic.shared.profiles.roles.details.roles.RoleItemColors
import majestic.shared.profiles.roles.details.roles.RoleList
import majestic.shared.profiles.roles.details.roles.RolesHeader
import majestic.shared.profiles.roles.details.roles.roleScreenHeader
import majestic.shared.profiles.roles.tools.BreadCrumb
import majestic.tooling.onClick
import org.jetbrains.compose.resources.painterResource

data class StationRolesColors(
    val background: Color,
    val header: RoleHeaderColors,
    val item: RoleItemColors,
    val divider: Color,
    val icon: ActionColors,
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
            modifier = Modifier.roleScreenHeader(colors = colors.header, orientation = orientation),
            orientation = orientation,
            count = station.roles.size,
            breadcrumbs = breadcrumbs,
            colors = colors.header,
            controller = controller,
            labels = labels.details
        )

        RoleList(
            modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState()),
            orientation = orientation,
            station = station,
            colors = colors,
            onRole = onRole,
            labels = labels.action,
            onUnassign = {}
        )
    }
    if (orientation is Portrait) Icon(
        modifier = Modifier
            .padding(20.dp)
            .align(Alignment.BottomEnd)
            .onClick { controller.open() }
            .clip(CircleShape)
            .pointerHoverIcon(icon = PointerIcon.Hand)
            .background(
                colors.icon.background.focused, CircleShape
            )
            .padding(16.dp)
            .size(20.dp),
        painter = painterResource(Res.drawable.ic_add),
        contentDescription = null,
        tint = colors.icon.foreground.focused
    )
}