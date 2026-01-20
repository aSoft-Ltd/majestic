package majestic.users.labels

import majestic.users.labels.dashboard.DashboardLabels
import majestic.users.labels.profile.ProfileLabels
import majestic.users.labels.table.TableLabels

data class UsersLabels(
    val dashboard: DashboardLabels,
    val profile: ProfileLabels,
    val table: TableLabels
)
