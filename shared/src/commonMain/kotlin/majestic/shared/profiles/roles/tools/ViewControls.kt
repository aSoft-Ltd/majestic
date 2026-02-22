package majestic.shared.profiles.roles.tools

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.ScreenOrientation
import majestic.icons.Res
import majestic.icons.ic_arrow_right
import majestic.shared.profiles.permissions.detail.ContainerPadding
import majestic.shared.profiles.permissions.detail.breadCrumbTab
import majestic.shared.profiles.tools.BreadCrumbTab
import majestic.shared.profiles.tools.BreadCrumbTabColors
import majestic.tooling.onClick
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.vectorResource

data class ViewControlsColors(
    val background: Color,
    val subtitle: Color,
    val breadCrumb: BreadCrumbTabColors,
)

internal data class BreadCrumb(
    val icon: DrawableResource,
    val label: String,
    val action: (() -> Unit)? = null
)

@Composable
internal fun ViewControls(
    modifier: Modifier,
    breadcrumbs: List<BreadCrumb>,
    colors: ViewControlsColors,
    orientation: ScreenOrientation
) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
    verticalAlignment = Alignment.CenterVertically
) {
    breadcrumbs.forEachIndexed { index, crumb ->
        val isLast = index == breadcrumbs.lastIndex

        BreadCrumbTab(
            modifier = Modifier
                .breadCrumbTab(
                    container = colors.background,
                    orientation = orientation,
                    paddings = ContainerPadding(end = 50.dp)
                )
                .onClick { crumb.action?.invoke() },
            icon = crumb.icon,
            label = crumb.label,
            colors = colors.breadCrumb,
            showLabel = orientation is Landscape || isLast,
            orientation = orientation,
        )

        if (!isLast) {
            Icon(
                imageVector = vectorResource(Res.drawable.ic_arrow_right),
                contentDescription = null,
                tint = colors.subtitle,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}