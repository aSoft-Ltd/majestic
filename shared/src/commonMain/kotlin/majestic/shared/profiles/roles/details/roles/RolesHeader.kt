package majestic.shared.profiles.roles.details.roles

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.ScreenOrientation
import majestic.shared.profiles.roles.assign.tools.AssignmentController
import majestic.shared.profiles.roles.details.header.RolesHeaderLabels
import majestic.shared.profiles.roles.details.header.TopHeader
import majestic.shared.profiles.roles.details.header.TopHeaderColors
import majestic.shared.profiles.roles.tools.BreadCrumb
import majestic.shared.profiles.roles.tools.ViewControls
import majestic.shared.profiles.roles.tools.ViewControlsColors

data class RoleHeaderColors(
    val background: Color,
    val top: TopHeaderColors,
    val view: ViewControlsColors,
)

internal fun Modifier.roleScreenHeader(
    colors: RoleHeaderColors,
    orientation: ScreenOrientation
) = this
    .fillMaxWidth()
    .clip(
        RoundedCornerShape(
            topStart = if (orientation is Landscape) 10.dp else 0.dp,
            topEnd = if (orientation is Landscape) 10.dp else 0.dp
        )
    )
    .background(colors.background)

@Composable
internal fun RolesHeader(
    modifier: Modifier,
    orientation: ScreenOrientation,
    count: Int,
    labels: RolesHeaderLabels,
    breadcrumbs: List<BreadCrumb>,
    colors: RoleHeaderColors,
    controller: AssignmentController,
) = Column(
    modifier = modifier,
    verticalArrangement = Arrangement.Top,
    horizontalAlignment = Alignment.Start
) {
    if (orientation is Landscape) {
        TopHeader(
            modifier = Modifier
                .clip(
                    shape = RoundedCornerShape(
                        topStart = 10.dp,
                        topEnd = 10.dp
                    )
                )
                .fillMaxWidth()
                .background(colors.top.background)
                .padding(horizontal = 16.dp, vertical = 10.dp),
            colors = colors.top,
            count = count,
            labels = labels,
            controller = controller
        )
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            color = colors.top.separator,
            thickness = .5.dp
        )
    }
    ViewControls(
        modifier = Modifier
            .fillMaxWidth()
            .background(colors.view.background)
            .padding(
                horizontal = if (orientation is Landscape) 16.dp else 10.dp,
                vertical = if (orientation is Landscape) 8.dp else 10.dp
            ),
        breadcrumbs = breadcrumbs,
        colors = colors.view,
        orientation = orientation
    )
}
