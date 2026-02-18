package majestic.shared.profiles.roles

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.dialogs.flexible.FlexibleDialog
import majestic.editor.toolbar.underline
import majestic.shared.menu.OptionMenu
import majestic.shared.profiles.roles.assign.FormColors
import majestic.shared.profiles.roles.assign.RoleAssignment
import majestic.shared.profiles.roles.assign.form.Headings
import majestic.shared.profiles.roles.assign.tools.rememberAssignmentController
import majestic.shared.profiles.roles.data.RoleData
import majestic.shared.profiles.roles.data.RoleLabels
import majestic.shared.profiles.roles.data.RoleOption

data class RoleColors(
    val header: HeaderColors,
    val item: RoleItemColors,
    val form: FormColors,
    val separator: Color
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
        totalStations = stations,
        assignedStations = assignedStations
    )
    if (controller.isVisible) FlexibleDialog(
        colors = colors.form.modal,
        onDismiss = {
            controller.close()
        },
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(color = colors.form.background, shape = RoundedCornerShape(12.dp))
            .size(700.dp, 800.dp),
        orientation = orientation,
        bar = {
            Headings(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                    .background(
                        color = colors.form.headings.background,
                        shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
                    )
                    .fillMaxWidth()
                    .wrapContentHeight(),
                labels = labels.assignment,
                controller = controller,
                colors = colors.form.headings,
                onDismiss = { controller.close() }
            )
        }
    ) {
        RoleAssignment(
            modifier = Modifier
                .fillMaxSize(),
            colors = colors.form,
            labels = labels.assignment,
            controller = controller
        )
    }

    Header(
        modifier = Modifier.toHeader(orientation, colors),
        colors = colors.header,
        orientation = orientation,
        header = labels.header
    )
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .then(
                when (orientation) {
                    is Portrait -> Modifier.fillMaxWidth()
                    is Landscape -> Modifier.wrapContentSize()
                }
            ),
        verticalArrangement = if (orientation is Landscape) Arrangement.Top else Arrangement.spacedBy(
            4.dp
        )
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
}
