package majestic.users.labels.dashboard

import majestic.users.labels.table.actions.UsersActionLabels

data class UserSummaryLabel(
    val title: String,
    val manage: String,
    val add: String,
    val actions: UsersActionLabels
) {
    companion object {
        val english by lazy {
            UserSummaryLabel(
                title = "Users",
                manage = "Manage",
                add = "Add",
                actions = UsersActionLabels.english
            )
        }
        val swahili by lazy {
            UserSummaryLabel(
                title = "Mwisho",
                manage = "Simamia",
                add = "Ongeza",
                actions = UsersActionLabels.swahili
            )
        }
    }
}
