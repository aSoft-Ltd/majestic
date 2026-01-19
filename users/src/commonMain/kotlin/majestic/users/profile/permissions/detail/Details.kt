package majestic.users.profile.permissions.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
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
import majestic.users.labels.profile.PermissionLabels
import majestic.users.profile.permissions.PermissionScreen
import majestic.users.tools.data.Permission
import org.jetbrains.compose.resources.DrawableResource


internal fun Modifier.header(
    orientation: ScreenOrientation,
    colors: DetailColors
) = then(
    other = when (orientation) {
        is Landscape -> Modifier.wrapContentSize()
        is Portrait -> Modifier
            .fillMaxWidth()
            .background(color = colors.background.copy(.05f))
    }
)
    .padding(horizontal = 20.dp, vertical = 10.dp)
internal data class DetailedProperties(
    val trailingIcon: DrawableResource,
    val trailingTitle: String,
    val permissions: List<Permission>
)

data class DetailColors(
    val tint: Color,
    val background: Color,
    val breadCrumb: (tint: Color?) -> BreadCrumbTabColors,
    val detail: DetailedItemColors
)

@Composable
internal fun Details(
    orientation: ScreenOrientation,
    props: DetailedProperties,
    colors: DetailColors,
    labels: PermissionLabels,
    modifier: Modifier = Modifier,
    current: PermissionScreen
) = Column(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(20.dp),
    horizontalAlignment = Alignment.Start
) {
    Header(
        modifier = Modifier.header(orientation, colors),
        props = props,
        orientation = orientation,
        current = current,
        labels = labels,
        colors = colors.toHeaderDetailColors()
    )
    Content(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        props = props,
        colors = colors,
        orientation = orientation
    )
}