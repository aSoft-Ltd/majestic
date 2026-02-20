package majestic.shared.profiles.roles

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.Landscape
import composex.screen.orientation.ScreenOrientation
import majestic.Search
import majestic.button.Button
import majestic.icons.Res
import majestic.icons.ic_access
import majestic.icons.ic_account_setting
import majestic.icons.ic_add
import majestic.icons.ic_admission
import majestic.icons.ic_arrow_right
import majestic.icons.ic_search
import majestic.shared.profiles.permissions.detail.ContainerPadding
import majestic.shared.profiles.permissions.detail.breadCrumbTab
import majestic.shared.profiles.roles.assign.tools.AssignmentController
import majestic.shared.profiles.tools.BreadCrumbTab
import majestic.tooling.onClick
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.vectorResource

data class RolesHeaderLabels(
    val search: String,
    val roles: String
)

internal data class BreadCrumbTab(
    val title: String,
    val icon: DrawableResource,
    val action: () -> Unit
)

internal fun Modifier.roleScreenHeader(
    colors: RoleHeaderColors,
    orientation: ScreenOrientation
) = this
    .fillMaxWidth()
    .clip(RoundedCornerShape(10.dp))
    .background(colors.background)
    .padding(if (orientation is Landscape) 20.dp else 10.dp)

@Composable
internal fun RolesHeader(
    modifier: Modifier,
    orientation: ScreenOrientation,
    tabs: List<BreadCrumbTab>,
    labels: RolesHeaderLabels,
    colors: RoleHeaderColors,
    count: Int,
    controller: AssignmentController
) = Column(
    modifier = modifier.horizontalScroll(rememberScrollState()),
    verticalArrangement = Arrangement.Top,
    horizontalAlignment = Alignment.CenterHorizontally
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(6.dp)
    ) {
        Row(
            modifier = Modifier.wrapContentSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_account_setting),
                contentDescription = null,
                tint = colors.icon,
                modifier = Modifier.size(16.dp)
            )
            Text(
                text = "$count ${labels.roles}",
                color = colors.icon,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium
            )
        }
        Search(
            modifier = Modifier
                .width(250.dp)
                .height(36.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(color = colors.search.searchField, shape = RoundedCornerShape(20.dp))
                .padding(horizontal = 16.dp),
            value = controller.searchQuery,
            onChange = { controller.searchQuery = it },
            hint = labels.search,
            colors = colors.search.default,
            icon = {
                val interaction = remember { MutableInteractionSource() }
                val hovered by interaction.collectIsHoveredAsState()
                Icon(
                    painter = painterResource(Res.drawable.ic_search),
                    contentDescription = null,
                    tint = colors.search.icon.foreground.unfocused,
                    modifier = Modifier
                        .padding(2.dp)
                        .hoverable(interaction)
                        .background(
                            color = when (hovered) {
                                true -> colors.search.icon.background.focused
                                false -> colors.search.icon.background.unfocused
                            },
                            shape = CircleShape
                        )
                        .padding(8.dp)
                        .size(24.dp)
                )
            }
        )
        Button(
            modifier = Modifier.clip(RoundedCornerShape(8.dp)),
            label = {
                Icon(
                    painter = painterResource(Res.drawable.ic_add),
                    contentDescription = null,
                    tint = colors.search.icon.foreground.unfocused
                )
            }
        )
    }

    Row(
        modifier = Modifier.wrapContentSize(),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BreadCrumbTab(
            modifier = Modifier
                .breadCrumbTab(
                    container = colors.breadCrumb.background,
                    orientation = orientation,
                    paddings = ContainerPadding(end = 50.dp)
                )
                .onClick {

                },
            showLabel = orientation is Landscape,
            icon = Res.drawable.ic_access,
            label = labels.breadcrumb,
            colors = colors.breadCrumb
        )
        if (orientation is Landscape) Icon(
            imageVector = vectorResource(Res.drawable.ic_arrow_right),
            contentDescription = null,
            tint = colors.tint,
            modifier = Modifier.size(12.dp)
        )
    }
}
