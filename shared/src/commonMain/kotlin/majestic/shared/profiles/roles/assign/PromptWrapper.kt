package majestic.shared.profiles.roles.assign

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.dialogs.flexible.FlexibleDialog
import majestic.shared.profiles.roles.RoleColors
import majestic.shared.profiles.roles.assign.form.Header
import majestic.shared.profiles.roles.assign.tools.AssignmentController
import majestic.shared.profiles.roles.data.RoleLabels

@Composable
internal fun PromptWrapper(
    controller: AssignmentController,
    colors: RoleColors,
    orientation: ScreenOrientation,
    labels: RoleLabels
) {
    if (controller.isVisible) FlexibleDialog(
        onDismiss = {
            controller.close()
        },
        modifier = when (orientation) {
            is Portrait -> Modifier.fillMaxSize().background(color = colors.form.background)
            is Landscape -> Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(color = colors.form.background, shape = RoundedCornerShape(12.dp))
                .size(700.dp, 800.dp)
        },
        bar = {
            Header(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                    .fillMaxWidth()
                    .wrapContentHeight(),
                labels = labels.assignment,
                controller = controller,
                colors = colors.form.header,
                onDismiss = { controller.close() },
                orientation = orientation
            )
        }
    ) {
        RoleAssignment(
            modifier = Modifier.fillMaxSize(),
            colors = colors.form,
            labels = labels.assignment,
            controller = controller,
            orientation = orientation
        )
    }
}