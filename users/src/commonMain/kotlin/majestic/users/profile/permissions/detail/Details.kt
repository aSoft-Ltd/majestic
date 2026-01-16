package majestic.users.profile.permissions.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import composex.screen.orientation.ScreenOrientation
import majestic.users.profile.permissions.PermissionScreen
import majestic.users.tools.data.Permission
import org.jetbrains.compose.resources.DrawableResource

data class DetailedDrawables(
    val leadingIcon: DrawableResource,
    val rightAngle: DrawableResource,
    val trailIcon: DrawableResource
)

data class DetailedColors(
    val rightAngle: Color,
    val breadCrumbTab: BreadCrumbTabColors,
    val detailedItem: DetailedItemColors,
    val border: Color,
    val container: Color,
    val separator: Color
)

data class DetailedLabels(
    val leadTitle: String,
    val trailingTitle: String,
)

data class DetailedProperties(
    val drawables: DetailedDrawables,
    val colors: DetailedColors,
    val labels: DetailedLabels,
    val permissions: List<Permission>
)

@Composable
internal fun Details(
    orientation: ScreenOrientation,
    props: DetailedProperties,
    modifier: Modifier = Modifier,
    current: PermissionScreen
) = Column(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(20.dp),
    horizontalAlignment = Alignment.Start
) {
    Header(
        modifier = Modifier.wrapContentSize(),
        props = props,
        current = current,
        orientation = orientation
    )
    Body(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        props = props
    )
}