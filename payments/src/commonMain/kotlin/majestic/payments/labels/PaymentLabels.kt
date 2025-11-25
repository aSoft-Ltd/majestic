package majestic.payments.labels

data class PaymentLabels(
    val header: DashboardHeaderLabels,
    val summary: SummaryLabels
) {
    companion object {
        val english by lazy {
            PaymentLabels(
                header = DashboardHeaderLabels.english,
                summary = SummaryLabels.english
            )
        }

        val swahili by lazy {
            PaymentLabels(
                header = DashboardHeaderLabels.swahili,
                summary = SummaryLabels.swahili
            )
        }
    }
}
