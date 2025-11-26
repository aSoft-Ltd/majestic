package majestic.payments.labels

data class PaymentLabels(
    val header: DashboardHeaderLabels,
    val summary: SummaryLabels,
    val filter: FilterLabels
) {
    companion object {
        val english by lazy {
            PaymentLabels(
                header = DashboardHeaderLabels.english,
                summary = SummaryLabels.english,
                filter = FilterLabels.english
            )
        }

        val swahili by lazy {
            PaymentLabels(
                header = DashboardHeaderLabels.swahili,
                summary = SummaryLabels.swahili,
                filter = FilterLabels.swahili
            )
        }
    }
}
