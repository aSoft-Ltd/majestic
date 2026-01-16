package majestic.users.profile.permissions.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.ScreenOrientation
import majestic.tooling.onClick
import majestic.users.labels.profile.PermissionLabels
import majestic.users.profile.permissions.PermissionScreen
import org.jetbrains.compose.resources.vectorResource
import tz.co.asoft.majestic_users.generated.resources.Res
import tz.co.asoft.majestic_users.generated.resources.ic_access
import tz.co.asoft.majestic_users.generated.resources.ic_admission
import tz.co.asoft.majestic_users.generated.resources.ic_arrow_right

private fun Modifier.breadCrumbTab(
    container: Color,
    orientation: ScreenOrientation,
    paddings: ContainerPadding = ContainerPadding()
) = this
    .clip(RoundedCornerShape(8.dp))
    .wrapContentSize()
    .background(
        shape = RoundedCornerShape(8.dp),
        color = if (orientation is Landscape) container else Color.Transparent
    )
    .padding(
        top = if (orientation is Landscape) paddings.top else 0.dp,
        bottom = if (orientation is Landscape) paddings.bottom else 0.dp,
        start = if (orientation is Landscape) paddings.start else 0.dp,
        end = if (orientation is Landscape) paddings.end else 0.dp
    )

data class NavigationColors(
    val tinted: Color,
    val breadCrumb: (tint: Color?) -> BreadCrumbTabColors,
    val background: Color
)

@Composable
internal fun Navigation(
    modifier: Modifier,
    props: DetailedProperties,
    orientation: ScreenOrientation,
    colors: NavigationColors,
    current: PermissionScreen,
    labels: PermissionLabels
) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.spacedBy(2.dp, Alignment.Start),
    verticalAlignment = Alignment.CenterVertically
) {
    BreadCrumbTab(
        modifier = Modifier
            .breadCrumbTab(
                container = colors.background.copy(.05f),
                orientation = orientation,
                paddings = ContainerPadding(end = 50.dp)
            )
            .onClick {
                current.main()
            },
        showLabel = orientation is Landscape,
        icon = Res.drawable.ic_access,
        label = labels.breadcrumb,
        colors = colors.breadCrumb(null)
    )
    if (orientation is Landscape) Icon(
        imageVector = vectorResource(Res.drawable.ic_arrow_right),
        contentDescription = null,
        tint = colors.background,
        modifier = Modifier.size(12.dp)
    )
    BreadCrumbTab(
        modifier = Modifier.breadCrumbTab(
            container = colors.background.copy(.05f),
            orientation = orientation,
            paddings = ContainerPadding(end = 10.dp)
        ),
        showLabel = true,
        icon = Res.drawable.ic_admission,
        label = props.trailingTitle,
        colors = colors.breadCrumb(colors.tinted)
    )
}