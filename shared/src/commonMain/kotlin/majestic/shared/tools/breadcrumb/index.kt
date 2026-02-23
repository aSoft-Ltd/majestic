package majestic.shared.tools.breadcrumb

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
import majestic.shared.tools.breadcrumb.tools.BreadCrumbTabColors
import majestic.shared.tools.breadcrumb.tools.ContainerPadding
import majestic.shared.tools.breadcrumb.tools.breadCrumbTab
import majestic.tooling.onClick
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.vectorResource

data class BreadCrumbControlColors(
    val background: Color,
    val arrow: Color,
    val breadCrumb: BreadCrumbTabColors,
)

internal data class BreadCrumb(
    val icon: DrawableResource,
    val label: String,
    val action: (() -> Unit)? = null
)

@Composable
internal fun BreadCrumbControls(
    modifier: Modifier,
    breadcrumbs: List<BreadCrumb>,
    colors: BreadCrumbControlColors,
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
                    container = colors.breadCrumb.background,
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
                tint = colors.arrow,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}