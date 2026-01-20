package majestic.users.labels.table

import majestic.users.labels.table.actions.UsersActionLabels

data class UsersTableBodyLabels(
    val status: UsersStatusLabels,
    val actions: UsersActionLabels
)