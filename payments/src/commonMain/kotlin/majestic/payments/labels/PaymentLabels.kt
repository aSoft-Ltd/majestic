package majestic.payments.labels

import majestic.payments.labels.transaction.TransactionLabels
import majestic.payments.labels.wallet.WalletLabels

class PaymentLabels(
    val header: DashboardHeaderLabels,
    val summary: SummaryLabels,
    val filter: FilterLabels,
    val dashboard: DashboardLabels,
    val wallet: WalletLabels,
    val transaction: TransactionLabels
)
