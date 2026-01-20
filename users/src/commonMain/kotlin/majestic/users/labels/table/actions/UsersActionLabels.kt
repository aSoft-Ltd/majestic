package majestic.users.labels.table.actions

import majestic.users.labels.table.actions.reset.UsersResetLabels


data class UsersActionLabels(
    val view: String,
    val block: majestic.users.labels.table.actions.UsersDeleteLabels,
    val reset: majestic.users.labels.table.actions.reset.UsersResetLabels,
    val delete: majestic.users.labels.table.actions.UsersDeleteLabels
)