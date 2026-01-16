package majestic.users.labels

import majestic.users.labels.profile.ProfileLabels
import majestic.users.labels.table.TableLabels

data class UsersLabels(
    val profile: ProfileLabels,
    val table: TableLabels
) {
    companion object {
        val english by lazy {
            UsersLabels(
                profile = ProfileLabels.english,
                table = TableLabels.english
            )
        }
        val swahili by lazy {
            UsersLabels(
                profile = ProfileLabels.swahili,
                table = TableLabels.swahili
            )
        }
    }
}
