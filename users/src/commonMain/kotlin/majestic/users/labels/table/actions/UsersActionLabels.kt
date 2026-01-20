package majestic.users.labels.table.actions

import majestic.users.labels.table.actions.reset.UsersResetLabels


data class UsersActionLabels(
    val view: String,
    val block: UsersDeleteLabels,
    val reset: UsersResetLabels,
    val delete: UsersDeleteLabels
)