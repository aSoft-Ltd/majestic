package majestic.users.labels.table.actions

import majestic.users.labels.table.actions.reset.UsersResetLabels


data class UsersActionLabels(
    val view: String,
    val block: UsersDeleteLabels,
    val reset: UsersResetLabels,
    val delete: UsersDeleteLabels
) {
    companion object {
        val english by lazy {
            UsersActionLabels(
                view = "View",
                block = UsersBlockLabels.english,
                reset = UsersResetLabels.english,
                delete = UsersDeleteLabels.english
            )
        }
        val swahili by lazy {
            UsersActionLabels(
                view = "Tazama",
                block = UsersBlockLabels.english,
                reset = UsersResetLabels.swahili,
                delete = UsersDeleteLabels.swahili
            )
        }
    }
}
