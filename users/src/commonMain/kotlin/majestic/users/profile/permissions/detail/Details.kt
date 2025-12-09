package majestic.users.profile.permissions.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import composex.screen.orientation.ScreenOrientation
import majestic.users.tools.data.Permission
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.vectorResource

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
    val container: Color
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

private fun Modifier.breadCrumbTab(container: Color) = this
    .clip(RoundedCornerShape(10.dp))
    .wrapContentSize()
    .background(
        shape = RoundedCornerShape(10.dp),
        color = container
    )
    .padding(horizontal = 15.dp, vertical = 8.dp)

@Composable
fun Details(
    orientation: ScreenOrientation,
    props: DetailedProperties,
    modifier: Modifier = Modifier
) = Column(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(20.dp),
    horizontalAlignment = Alignment.Start
) {
    Row(
        modifier = Modifier.wrapContentSize(),
        horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BreadCrumbTab(
            modifier = Modifier.breadCrumbTab( container = props.colors.container),
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
            modifier = Modifier.breadCrumbTab( container = props.colors.container),
            orientation = orientation,
            icon = props.drawables.leadingIcon,
            label = props.labels.leadTitle,
            colors = props.colors.breadCrumbTab
        )
    }
    Column(
        modifier = Modifier.fillMaxWidth(.5f).fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        props.permissions.forEach { permission ->
            var switch by remember { mutableStateOf(permission.active) }
            println("$permission")
            DetailedItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(10.dp),
                props = DetailedItemProperties(
                    colors = props.colors.detailedItem,
                    item = permission,
                    itemState = switch
                ),
                onSwitching = {
                    switch = it
                }
            )
        }
    }
}