package majestic.shared.profiles.roles.details.roles.tools

import majestic.shared.tools.menu.OptionMenu
import majestic.shared.profiles.roles.details.roles.RoleAction
import majestic.shared.profiles.roles.details.roles.RoleActionLabels

internal fun RoleActionLabels.actions() = listOf(
    OptionMenu(
        label = RoleAction.View.getLabels(this),
        action = RoleAction.View
    ),
    OptionMenu(
        label = RoleAction.Unassign.getLabels(this),
        action = RoleAction.Unassign,
        isDestructive = true
    )
)