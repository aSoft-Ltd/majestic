package majestic.shared.users.label.table

import majestic.shared.users.label.table.actions.UsersActionLabels

data class UsersTableBodyLabels(
    val status: UsersStatusLabels,
    val actions: UsersActionLabels
)