
package majestic.shared.profiles.roles.details

import androidx.compose.foundation.background
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
import composex.screen.orientation.ScreenOrientation
import majestic.Search
import majestic.button.Button
import majestic.icons.Res
import majestic.icons.ic_access
import majestic.icons.ic_admission
import majestic.icons.ic_add
import majestic.icons.ic_arrow_right
import majestic.icons.ic_search
import majestic.shared.profiles.roles.assign.tools.AssignmentController
import majestic.shared.profiles.tools.BreadCrumbTab
import majestic.tooling.onClick
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.vectorResource

internal fun Modifier.roleScreenHeader(
    colors: RoleHeaderColors,
    orientation: ScreenOrientation
) = this
    .fillMaxWidth()
    .clip(RoundedCornerShape(10.dp))
    .background(colors.background)
    .padding(16.dp)

@Composable
internal fun RolesHeader(
    modifier: Modifier,
    orientation: ScreenOrientation,
    stationName: String,
    count: Int,
    searchLabel: String,
    rolesLabel: String,
    breadcrumbLabel: String,
    colors: RoleHeaderColors,
    controller: AssignmentController,
    onBack: () -> Unit
) = Column(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(16.dp),
    horizontalAlignment = Alignment.Start
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_admission),
                contentDescription = null,
                tint = colors.icon,
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = "$count $rolesLabel",
                color = colors.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Search(
                modifier = Modifier
                    .width(250.dp)
                    .height(36.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(color = colors.search.searchField, shape = RoundedCornerShape(20.dp))
                    .padding(horizontal = 16.dp),
                value = controller.searchQuery,
                onChange = { controller.searchQuery = it },
                hint = searchLabel,
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
                                color = if (hovered) colors.search.icon.background.focused else colors.search.icon.background.unfocused,
                                shape = CircleShape
                            )
                            .padding(8.dp)
                            .size(24.dp)
                    )
                }
            )

            Button(
                modifier = Modifier.size(36.dp).clip(CircleShape),
                label = {
                    Icon(
                        painter = painterResource(Res.drawable.ic_add),
                        contentDescription = null,
                        tint = colors.search.icon.foreground.unfocused
                    )
                }
            )
        }
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BreadCrumbTab(
            modifier = Modifier.onClick { onBack() },
            icon = Res.drawable.ic_access,
            label = breadcrumbLabel,
            colors = colors.breadCrumb,
            showLabel = true
        )

        Icon(
            imageVector = vectorResource(Res.drawable.ic_arrow_right),
            contentDescription = null,
            tint = colors.subtitle,
            modifier = Modifier.size(12.dp)
        )

        BreadCrumbTab(
            modifier = Modifier,
            icon = Res.drawable.ic_admission,
            label = "$stationName' Roles",
            colors = colors.breadCrumb,
            showLabel = true
        )
    }
}
