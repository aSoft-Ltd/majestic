package majestic.users.profile.roles

import androidx.compose.runtime.Composable
import composex.screen.orientation.ScreenOrientation
import majestic.users.labels.roles.RolesLabels
import majestic.users.profile.roles.campus.Campus
import majestic.users.profile.roles.campus.CampusMenuAction

@Composable
internal fun CampusesList(
    campuses: List<CampusData>,
    labels: RolesLabels,
    colors: RolesColors,
    orientation: ScreenOrientation,
    onCampusAction: (String, CampusMenuAction) -> Unit
) {
    campuses.forEach { campus ->
        Campus(
            campusName = campus.name,
            rolesCount = campus.rolesCount,
            labels = labels,
            colors = colors.campus,
            orientation = orientation,
            onAddRole = { onCampusAction(campus.id, CampusMenuAction.AddRole) },
            onViewRole = { onCampusAction(campus.id, CampusMenuAction.ViewRole) },
            onEditRole = { onCampusAction(campus.id, CampusMenuAction.EditRole) },
            onDeleteRole = { onCampusAction(campus.id, CampusMenuAction.DeleteRole) }
        )
    }
}