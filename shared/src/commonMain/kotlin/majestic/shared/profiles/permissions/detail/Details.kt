package majestic.shared.profiles.permissions.detail

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
import majestic.icons.Res
import majestic.icons.ic_access
import majestic.shared.profiles.Action
import majestic.shared.profiles.permissions.PermissionScreen
import majestic.shared.tools.breadcrumb.BreadCrumb
import majestic.shared.tools.breadcrumb.BreadCrumbControlColors
import majestic.shared.tools.breadcrumb.BreadCrumbControls
import majestic.shared.users.label.contacts.PermissionLabels
import org.jetbrains.compose.resources.DrawableResource


internal fun Modifier.header(
    orientation: ScreenOrientation,
    background: Color
) = then(
    other = when (orientation) {
        is Landscape -> Modifier.wrapContentSize()
        is Portrait -> Modifier
            .fillMaxWidth()
            .background(color = background)
    }
)
    .padding(horizontal = 20.dp, vertical = 10.dp)

internal data class DetailedProperties(
    val trailingIcon: DrawableResource,
    val trailingTitle: String,
    val permissions: List<Action>
)

data class DetailColors(
    val tint: Color,
    val separator: Color,
    val view: BreadCrumbControlColors,
    val body: DetailedItemColors
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
    BreadCrumbControls(
        modifier = Modifier.header(orientation, colors.view.background),
        breadcrumbs = listOf(
            BreadCrumb(
                icon = Res.drawable.ic_access,
                label = labels.breadcrumb,
                action = { current.main() }
            ),
            BreadCrumb(
                icon = props.trailingIcon,
                label = props.trailingTitle
            )
        ),
        colors = colors.view,
        orientation = orientation
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