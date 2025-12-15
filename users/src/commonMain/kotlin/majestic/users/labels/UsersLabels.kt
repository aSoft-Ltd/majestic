package majestic.users.labels

import majestic.users.labels.dashboard.DashboardLabels
import majestic.users.labels.profile.UserProfileLabels
import majestic.users.labels.table.UsersTableLabels

data class UsersLabels(
    val dashboard: DashboardLabels,
    val profile: UserProfileLabels,
    val table: UsersTableLabels
) {
    companion object {
        val english by lazy {
            UsersLabels(
                dashboard = DashboardLabels.english,
                profile = UserProfileLabels.english,
                table = UsersTableLabels.english
            )
        }
        val swahili by lazy {
            UsersLabels(
                dashboard = DashboardLabels.swahili,
                profile = UserProfileLabels.swahili,
                table = UsersTableLabels.swahili
            )
        }
    }
}
