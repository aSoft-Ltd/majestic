package majestic.shared.profiles.roles.assign.form

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.ScreenOrientation
import majestic.icons.Res
import majestic.icons.ic_account_setting
import majestic.shared.profiles.roles.assign.tools.AssignmentController
import majestic.shared.profiles.roles.data.RoleAssignmentLabels
import majestic.shared.tools.modal.ModalColors
import majestic.shared.tools.modal.ModalHeader
import majestic.shared.tools.modal.modalHeaderStyle
import org.jetbrains.compose.resources.vectorResource



@Composable
internal fun Header(
    modifier: Modifier,
    labels: RoleAssignmentLabels,
    controller: AssignmentController,
    orientation: ScreenOrientation,
    colors: ModalColors,
    onDismiss: () -> Unit
) = Column(
    modifier = modifier,
    verticalArrangement = Arrangement.Top,
    horizontalAlignment = Alignment.CenterHorizontally
) {
    ModalHeader(
        icon = vectorResource(Res.drawable.ic_account_setting),
        title = "${labels.title} ${controller.userName}",
        onClose = onDismiss,
        colors = colors,
        orientation = orientation,
        modifier = Modifier.modalHeaderStyle(colors = colors)
    )
    if (orientation is Landscape) HorizontalDivider(
        modifier = Modifier.fillMaxWidth(),
        thickness = .5.dp,
        color = colors.separator
    )
    SubHeader(
        modifier = Modifier
            .fillMaxWidth()
            .background(colors.subheader)
            .padding(horizontal = if (orientation is Landscape) 16.dp else 10.dp, vertical = 10.dp),
        colors = colors,
        controller = controller,
        labels = labels,
        orientation = orientation
    )
}