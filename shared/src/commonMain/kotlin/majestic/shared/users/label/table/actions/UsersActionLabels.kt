package majestic.shared.users.label.table.actions

import majestic.shared.users.label.table.actions.reset.UsersResetLabels


data class UsersActionLabels(
    val view: String,
    val block: UsersBlockLabels,
    val reset: UsersResetLabels,
    val delete: UsersDeleteLabels
)