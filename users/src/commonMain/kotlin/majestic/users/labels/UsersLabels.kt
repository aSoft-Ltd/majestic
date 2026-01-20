package majestic.users.labels

import majestic.users.labels.profile.ProfileLabels
import majestic.users.labels.table.TableLabels

data class UsersLabels(
    val profile: ProfileLabels,
    val table: TableLabels
)
