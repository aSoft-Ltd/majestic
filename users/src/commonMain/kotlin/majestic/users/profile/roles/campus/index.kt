package majestic.users.profile.roles.campus

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import composex.screen.orientation.ScreenOrientation
import majestic.users.labels.roles.RolesLabels
import majestic.users.profile.roles.CampusColors

enum class CampusMenuAction {
    AddRole, ViewRole, EditRole, DeleteRole
}

@Composable
internal fun Campus(
    campusName: String,
    rolesCount: Int,
    labels: RolesLabels,
    colors: CampusColors,
    orientation: ScreenOrientation,
    onAddRole: () -> Unit,
    onViewRole: () -> Unit,
    onEditRole: () -> Unit,
    onDeleteRole: () -> Unit,
    modifier: Modifier = Modifier
) {
    CampusContainer(
        modifier = modifier,
        orientation = orientation,
        colors = colors,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)
        ) {
            CampusIcon(colors = colors)
            CampusInfo(
                campusName = campusName,
                rolesCount = rolesCount,
                labels = labels.campus,
                theme = colors.theme
            )
        }

        CampusActions(
            labels = labels,
            colors = colors,
            orientation = orientation,
            onAddRole = onAddRole,
            onViewRole = onViewRole,
            onEditRole = onEditRole,
            onDeleteRole = onDeleteRole
        )
    }
}