package majestic.shared.profiles.roles.assign.form

import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.Landscape
import composex.screen.orientation.ScreenOrientation
import majestic.Search
import majestic.SearchDefaultColors
import majestic.editor.tools.StateColors
import majestic.icons.Res
import majestic.icons.ic_account_setting
import majestic.icons.ic_search
import majestic.shared.profiles.roles.assign.tools.AssignmentController
import majestic.shared.profiles.roles.data.RoleAssignmentLabels
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.vectorResource

data class ActionColors(
    val foreground: StateColors,
    val background: StateColors
)

data class SearchColors(
    val default: SearchDefaultColors,
    val searchField: Color,
    val icon: ActionColors,
)

data class SubHeaderColors(
    val tint: Color,
    val stats: Color,
    val search: SearchColors,
    val background: Color
)

@Composable
internal fun SubHeader(
    modifier: Modifier,
    colors: SubHeaderColors,
    orientation: ScreenOrientation,
    controller: AssignmentController,
    labels: RoleAssignmentLabels
) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start)
    ) {
        Icon(
            painter = painterResource(Res.drawable.ic_account_setting),
            contentDescription = null,
            tint = colors.tint,
            modifier = Modifier.size(16.dp)
        )
        Text(
            text = "${controller.selectedRoles.size}/${controller.allRoles.size} ${labels.selected}",
            color = colors.stats,
            fontSize = 14.sp
        )
    }

    if (orientation is Landscape) Search(
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
    else Icon(
        imageVector = vectorResource(Res.drawable.ic_search),
        tint = colors.search.icon.foreground.unfocused,
        contentDescription = null,
        modifier = Modifier.size(24.dp)
    )
}