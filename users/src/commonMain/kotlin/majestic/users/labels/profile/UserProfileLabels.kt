package majestic.users.labels.profile

data class UserProfileLabels(
    val tabs: UserTabLabels,
    val header: UserHeaderLabels,
) {
    companion object {
        val english by lazy {
            UserProfileLabels(
                tabs = UserTabLabels.english,
                header = UserHeaderLabels.english
            )
        }
        val swahili by lazy {
            UserProfileLabels(
                tabs = UserTabLabels.swahili,
                header = UserHeaderLabels.swahili
            )
        }
    }
}
