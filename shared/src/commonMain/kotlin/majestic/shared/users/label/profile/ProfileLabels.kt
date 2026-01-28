package majestic.shared.users.label.profile

import majestic.shared.users.UserActionLabels

data class ProfileLabels(
    val tabs: TabLabels,
    val header: HeaderLabels,
    val actions: UserActionLabels
)