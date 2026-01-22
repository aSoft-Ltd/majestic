package users.label.table.actions

import users.label.table.actions.reset.UsersResetLabels


data class UsersActionLabels(
    val view: String,
    val block: UsersDeleteLabels,
    val reset: UsersResetLabels,
    val delete: UsersDeleteLabels
)