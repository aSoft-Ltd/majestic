package majestic.shared.users

import majestic.shared.users.label.dashboard.DashboardLabels
import majestic.shared.users.label.profile.ProfileLabels
import majestic.shared.users.label.table.TableLabels

data class UsersLabels(
    val dashboard: DashboardLabels,
    val profile: ProfileLabels,
    val table: TableLabels
)
