package majestic.shared.profiles.roles.details.permissions

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.ScreenOrientation
import majestic.shared.profiles.roles.data.Role
import majestic.shared.profiles.roles.tools.BreadCrumb
import majestic.shared.profiles.roles.tools.ViewControls
import majestic.shared.profiles.roles.tools.ViewControlsColors


data class RoleDetailsColors(
    val background: Color,
    val view: ViewControlsColors,
    val responsibility: ResponsibilityColors,
    val divider: Color
)

@Composable
internal fun PermissionDetails(
    modifier: Modifier,
    orientation: ScreenOrientation,
    role: Role,
    colors: RoleDetailsColors,
    breadcrumbs: List<BreadCrumb>,
) = Column(
    modifier = modifier,
    verticalArrangement = Arrangement.Top
) {
    ViewControls(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = colors.background,
                shape = RoundedCornerShape(
                    topStart = if (orientation is Landscape) 10.dp else 0.dp,
                    topEnd = if (orientation is Landscape) 10.dp else 0.dp
                )
            )
            .padding(if (orientation is Landscape) 16.dp else 10.dp),
        breadcrumbs = breadcrumbs,
        colors = colors.view,
        orientation = orientation
    )

    Permissions(
        modifier = Modifier
            .wrapContentSize()
            .verticalScroll(rememberScrollState()),
        responsibilities = role.permissions,
        colors = colors.responsibility,
        orientation = orientation
    )
}