package majestic.users.labels.profile

data class UserProfileLabels(
    val tabs: TabLabels,
    val header: HeaderLabels,
) {
    companion object {
        val english by lazy {
            UserProfileLabels(
                tabs = TabLabels.english,
                header = HeaderLabels.english
            )
        }
        val swahili by lazy {
            UserProfileLabels(
                tabs = TabLabels.swahili,
                header = HeaderLabels.swahili
            )
        }
    }
}
