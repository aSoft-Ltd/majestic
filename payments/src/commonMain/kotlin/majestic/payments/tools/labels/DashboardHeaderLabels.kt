package majestic.payments.tools.labels

data class DashboardHeaderLabels(
    val section: SectionLabels
) {
    companion object {
        val english by lazy {
            DashboardHeaderLabels(
                section = SectionLabels(
                    label = "Payment Insights",
                    description = "Explore the metrics to help you track, and act on trends"
                )
            )
        }

        val swahili by lazy {
            DashboardHeaderLabels(
                section = SectionLabels(
                    label = "Uchambuzi wa malipo",
                    description = "Chunguza vipimo kukusaidia kufuatilia, na kuchukua hatua kwa mwenendo"
                )
            )
        }
    }
}
