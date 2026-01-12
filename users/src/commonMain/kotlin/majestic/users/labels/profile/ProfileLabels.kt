package majestic.users.labels.profile

import majestic.users.labels.roles.RolesLabels

data class ProfileLabels(
    val tabs: TabLabels,
    val header: HeaderLabels,
    val roles: RolesLabels
) {
    companion object {
        val english by lazy {
            ProfileLabels(
                tabs = TabLabels.english,
                header = HeaderLabels.english,
                roles = RolesLabels.english,
            )
        }
        val swahili by lazy {
            ProfileLabels(
                tabs = TabLabels.swahili,
                header = HeaderLabels.swahili,
                roles = RolesLabels.swahili,
                )
        }
    }
}
