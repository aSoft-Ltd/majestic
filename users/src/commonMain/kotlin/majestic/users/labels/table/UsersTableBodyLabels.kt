package majestic.users.labels.table

import majestic.users.labels.table.actions.UsersActionLabels

data class UsersTableBodyLabels(
    val status: UsersStatusLabels,
    val actions: UsersActionLabels
) {
    companion object {
        val english by lazy {
            UsersTableBodyLabels(
                status = UsersStatusLabels.english,
                actions = UsersActionLabels.english
            )
        }
        val swahili by lazy {
            UsersTableBodyLabels(
                status = UsersStatusLabels.swahili,
                actions = UsersActionLabels.swahili
            )
        }
    }
}
