package majestic.payments.labels

import majestic.payments.labels.transaction.TransactionLabels

data class PaymentLabels(
    val header: DashboardHeaderLabels,
    val summary: SummaryLabels,
    val filter: FilterLabels,
    val dashboard: DashboardLabels,
    val wallet: WalletLabels,
    val transaction: TransactionLabels
) {
    companion object {
        val english by lazy {
            PaymentLabels(
                header = DashboardHeaderLabels.english,
                summary = SummaryLabels.english,
                filter = FilterLabels.english,
                dashboard = DashboardLabels.english,
                wallet = WalletLabels.english,
                transaction = TransactionLabels.english
            )
        }

        val swahili by lazy {
            PaymentLabels(
                header = DashboardHeaderLabels.swahili,
                summary = SummaryLabels.swahili,
                filter = FilterLabels.swahili,
                dashboard = DashboardLabels.swahili,
                wallet = WalletLabels.swahili,
                transaction = TransactionLabels.swahili
            )
        }
    }
}
