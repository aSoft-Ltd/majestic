package majestic.users.dashboard.roles.tools

import androidx.compose.ui.graphics.Color
import majestic.users.dashboard.roles.UsersRolesProps
import majestic.users.labels.dashboard.RoleActionsLabels
import majestic.users.labels.dashboard.RoleLabels
import majestic.users.tools.menu.MenuOptionColors
import majestic.users.tools.menu.OptionMenu
import tz.co.asoft.majestic_users.generated.resources.Res
import tz.co.asoft.majestic_users.generated.resources.ic_key
import tz.co.asoft.majestic_users.generated.resources.ic_user_multiple

enum class RoleAction {
    View, Edit, Duplicate, Delete
}

internal fun RoleActionsLabels.getRoles() = listOf(
    OptionMenu(view, RoleAction.View),
    OptionMenu(edit, RoleAction.Edit),
    OptionMenu(duplicate, RoleAction.Duplicate),
    OptionMenu(delete, RoleAction.Delete)
)

data class RoleHeaderColors(
    val title: Color,
    val menu: MenuOptionColors,
)

internal fun UsersRolesProps.stats(
    role: UserRole
) = listOf(
    Stat(
        icon = Res.drawable.ic_key,
        iconContentDescription = "Key Icon",
        title = body.labels.permission,
        value = role.permissions
    ),
    Stat(
        icon = Res.drawable.ic_user_multiple,
        iconContentDescription = "Users Icon",
        title = body.labels.userAssigned,
        value = role.usersAssigned
    )
)