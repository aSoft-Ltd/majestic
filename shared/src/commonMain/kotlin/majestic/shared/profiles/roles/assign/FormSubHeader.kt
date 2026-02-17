package majestic.shared.profiles.roles.assign

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.icons.Res
import majestic.icons.ic_search
import majestic.icons.ic_vertical_settings
import majestic.search.SearchField
import majestic.shared.profiles.roles.data.RoleAssignmentLabels
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun FormSubHeader(
    controller: AssignmentController,
    colors: RoleAssignmentColors,
    labels: RoleAssignmentLabels
) = Row(
    modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 16.dp),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(Res.drawable.ic_vertical_settings),
            contentDescription = null,
            tint = colors.statsIcon,
            modifier = Modifier.size(16.dp)
        )
        Spacer(Modifier.width(8.dp))
        val selectedCount = controller.selectedRoles.size
        val totalCount = controller.allRoles.size
        Text(
            text = "$selectedCount/$totalCount ${labels.selected}",
            color = colors.stats,
            fontSize = 14.sp
        )
    }

    SearchField(
        modifier = Modifier
            .width(250.dp)
            .height(36.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(colors.search.background)
            .padding(horizontal = 16.dp),
        value = controller.searchQuery,
        onChange = { controller.searchQuery = it },
        hint = labels.search,
        theme = majestic.Dark,
        icon = {
            Icon(
                painter = painterResource(Res.drawable.ic_search),
                contentDescription = null,
                tint = colors.search.icon,
                modifier = Modifier.size(18.dp)
            )
        }
    )
}
