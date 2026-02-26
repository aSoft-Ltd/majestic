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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.ScreenOrientation
import majestic.shared.profiles.roles.assign.tools.AssignmentController
import majestic.shared.profiles.roles.data.RoleAssignmentLabels

data class HeadingsColor(
    val background: Color,
    val header: HeaderColors,
    val subheading: SubHeaderColors,
    val divider: Color
)

@Composable
internal fun Headings(
    modifier: Modifier,
    labels: RoleAssignmentLabels,
    controller: AssignmentController,
    orientation: ScreenOrientation,
    colors: HeadingsColor,
    onDismiss: () -> Unit
) = Column(
    modifier = modifier,
    verticalArrangement = Arrangement.Top,
    horizontalAlignment = Alignment.CenterHorizontally
) {
    Header(
        modifier = Modifier
            .fillMaxWidth()
            .background(if (orientation is Landscape) colors.header.background else Color.Transparent)
            .padding(horizontal = 16.dp, vertical = 24.dp),
        labels = labels,
        controller = controller,
        colors = colors.header,
        onDismiss = onDismiss,
        orientation = orientation
    )
    if (orientation is Landscape) HorizontalDivider(
        modifier = Modifier.fillMaxWidth(),
        thickness = .5.dp,
        color = colors.divider
    )
    SubHeader(
        modifier = Modifier
            .fillMaxWidth()
            .background(colors.subheading.background)
            .padding(horizontal = if (orientation is Landscape) 16.dp else 10.dp, vertical = 10.dp),
        colors = colors.subheading,
        controller = controller,
        labels = labels,
        orientation = orientation
    )
}