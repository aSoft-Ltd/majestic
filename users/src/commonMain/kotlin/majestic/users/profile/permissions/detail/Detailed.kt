package majestic.users.profile.permissions.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import composex.screen.orientation.ScreenOrientation
import org.jetbrains.compose.resources.DrawableResource

data class DetailedDrawables(
    val leadingIcon: DrawableResource,
    val rightAngle: DrawableResource,
    val trailIcon: DrawableResource
)

data class DetailedColors(
    val leadingIcon: Color,
    val rightAngle: Color,
    val trailIcon: Color
)

data class DetailedLabels()
data class DetailedProperties(
    val drawables: DetailedDrawables,
    val colors: DetailedColors,
    val labels: DetailedLabels
)


@Composable
fun Detailed(
    orientation: ScreenOrientation,
    properties: DetailedProperties,
    modifier: Modifier = Modifier
) = Column(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(20.dp),
    horizontalAlignment = Alignment.CenterHorizontally
) {
    Row {
        BreadCrumbTab(
            modifier = Modifier.wrapContentSize(),
            icon = properties.leadingIcon,
            label = TODO(),
            colors = TODO()
        )
        Icon()
        BreadCrumbTab()
    }
    Column() {

    }
}