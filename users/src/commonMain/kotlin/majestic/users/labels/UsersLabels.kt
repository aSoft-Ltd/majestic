package majestic.users.labels

import majestic.users.labels.dashboard.DashboardLabels
import majestic.users.labels.profile.ProfileLabels
import majestic.users.labels.table.TableLabels

data class UsersLabels(
    val dashboard: DashboardLabels,
    val profile: ProfileLabels,
    val table: TableLabels
) {
    companion object {
        val english by lazy {
            UsersLabels(
                dashboard = DashboardLabels.english,
                profile = ProfileLabels.english,
                table = TableLabels.english
            )
        }
        val swahili by lazy {
            UsersLabels(
                dashboard = DashboardLabels.swahili,
                profile = ProfileLabels.swahili,
                table = TableLabels.swahili
            )
        }
    }
}
