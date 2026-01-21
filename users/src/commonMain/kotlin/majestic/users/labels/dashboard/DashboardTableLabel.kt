package majestic.users.labels.dashboard

import majestic.users.table.body.UserTableLabels

data class DashboardTableLabel(
    val title: String,
    val manage: String,
    val add: String,
//    val actions: UsersActionLabels,
    val table: UserTableLabels
)