package majestic.users.labels.dashboard

import majestic.users.labels.table.actions.UsersActionLabels

data class UserSummaryLabel(
    val title: String,
    val manage: String,
    val add: String,
    val actions: UsersActionLabels
)