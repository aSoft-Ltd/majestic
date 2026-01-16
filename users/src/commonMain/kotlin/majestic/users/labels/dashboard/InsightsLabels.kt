package majestic.users.labels.dashboard

data class InsightsLabels(
    val title: String,
    val detail: String
) {
    companion object {
        val english by lazy {
            InsightsLabels(
                title = "User Insights",
                detail = "Explore te metrics to help you track,and act on trends",
            )
        }
        val swahili by lazy {
            InsightsLabels(
             title = "Uelewa wa Watumiaji",
             detail = "Chunguza vipimo vinavyokusaidia kufuatilia na kuchukua hatua kulingana na mitindo",
            )
        }
    }
}
