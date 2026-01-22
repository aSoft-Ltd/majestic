package majestic.users.dashboard.tools.portraitHeader

import majestic.users.dashboard.tools.Roles
import majestic.users.dashboard.tools.Users
import majestic.users.dashboard.tools.View
import users.label.dashboard.DashboardTabLabels

internal data class TabListItem(val tab: String, val view: View)

internal fun DashboardTabLabels.getItems() = listOf(
    TabListItem(
        tab = users,
        view = Users,
    ),
    TabListItem(
        tab = roles,
        view = Roles,
    )
)

internal fun TabListItem.isSelected(view: View) = view == this.view