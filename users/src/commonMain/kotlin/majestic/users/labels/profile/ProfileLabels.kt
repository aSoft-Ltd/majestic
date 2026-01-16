package majestic.users.labels.profile

data class ProfileLabels(
    val tabs: TabLabels,
    val header: HeaderLabels
) {
    companion object {
        val english by lazy {
            ProfileLabels(
                tabs = TabLabels.english,
                header = HeaderLabels.english
            )
        }
        val swahili by lazy {
            ProfileLabels(
                tabs = TabLabels.swahili,
                header = HeaderLabels.swahili
            )
        }
    }
}
