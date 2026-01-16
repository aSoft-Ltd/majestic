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
import composex.screen.orientation.ScreenOrientation
import majestic.tooling.onClick
import majestic.users.profile.permissions.PermissionScreen
import org.jetbrains.compose.resources.vectorResource

private fun Modifier.breadCrumbTab(container: Color) = this
    .clip(RoundedCornerShape(10.dp))
    .wrapContentSize()
    .background(
        shape = RoundedCornerShape(10.dp),
        color = container
    )
    .padding(horizontal = 15.dp, vertical = 8.dp)

@Composable
internal fun Header(
    modifier: Modifier,
    props: DetailedProperties,
    current: PermissionScreen,
    orientation: ScreenOrientation
) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.Start),
    verticalAlignment = Alignment.CenterVertically
) {
    BreadCrumbTab(
        modifier = Modifier.breadCrumbTab(container = props.colors.container).onClick {
            current.main()
        },
        orientation = orientation,
        icon = props.drawables.leadingIcon,
        label = props.labels.leadTitle,
        colors = props.colors.breadCrumbTab
    )
    Icon(
        imageVector = vectorResource(props.drawables.rightAngle),
        contentDescription = null,
        tint = props.colors.rightAngle,
        modifier = Modifier.size(40.dp)
    )
    BreadCrumbTab(
        modifier = Modifier.breadCrumbTab(container = props.colors.container),
        orientation = orientation,
        icon = props.drawables.leadingIcon,
        label = props.labels.leadTitle,
        colors = props.colors.breadCrumbTab
    )
}