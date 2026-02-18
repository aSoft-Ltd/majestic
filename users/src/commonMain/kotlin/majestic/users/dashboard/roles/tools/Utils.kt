package majestic.users.dashboard.roles.tools

import androidx.compose.ui.graphics.Color
import majestic.icons.Res
import majestic.icons.ic_key
import majestic.icons.ic_user_multiple
import majestic.shared.menu.MenuOptionColors
import majestic.shared.menu.OptionMenu
import majestic.shared.users.label.dashboard.RoleActionsLabels
import majestic.users.dashboard.roles.UsersRolesProps

enum class RoleAction {
    View, Edit, Duplicate, Delete
}

internal fun RoleActionsLabels.getRoles() = listOf(
    OptionMenu(view, RoleAction.View),
    OptionMenu(edit, RoleAction.Edit),
    OptionMenu(duplicate, RoleAction.Duplicate),
    OptionMenu(delete, RoleAction.Delete)
)


internal fun UsersRolesProps.stats(
    role: UserRole
) = listOf(
    Stat(
        icon = Res.drawable.ic_key,
        description = "Key Icon",
        title = body.labels.permission,
        value = role.permissions
    ),
    Stat(
        icon = Res.drawable.ic_user_multiple,
        description = "Users Icon",
        title = body.labels.userAssigned,
        value = role.usersAssigned
    )
)