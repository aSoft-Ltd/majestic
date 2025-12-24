package majestic.users.profile.roles.campus

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.ScreenOrientation
import majestic.users.labels.roles.RolesLabels
import majestic.users.profile.roles.CampusColors
import majestic.users.tools.menu.MenuOption
import majestic.users.tools.menu.OptionMenu

@Composable
fun CampusActions(
    labels: RolesLabels,
    colors: CampusColors,
    orientation: ScreenOrientation,
    onAddRole: () -> Unit,
    onViewRole: () -> Unit,
    onEditRole: () -> Unit,
    onDeleteRole: () -> Unit
) {
    Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
        if (orientation is Landscape) {
            CampusAddButton(
                labels = labels.campus,
                colors = colors,
                onAddRole = onAddRole
            )
        }

        MenuOption(
            colors = colors.menuOption,
            orientation = orientation,
            actions = buildList {
                if (orientation !is Landscape) {
                    add(OptionMenu(labels.actions.addRole, CampusMenuAction.AddRole))
                }
                add(OptionMenu(labels.actions.viewRole, CampusMenuAction.ViewRole))
                add(OptionMenu(labels.actions.editRole, CampusMenuAction.EditRole))
                add(OptionMenu(labels.actions.deleteRole, CampusMenuAction.DeleteRole))
            }
        ) { action ->
            when (action) {
                CampusMenuAction.AddRole -> onAddRole()
                CampusMenuAction.ViewRole -> onViewRole()
                CampusMenuAction.EditRole -> onEditRole()
                CampusMenuAction.DeleteRole -> onDeleteRole()
            }
        }
    }
}