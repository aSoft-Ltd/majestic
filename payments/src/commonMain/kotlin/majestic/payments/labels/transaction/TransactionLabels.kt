package majestic.payments.labels.transaction

import majestic.payments.labels.MenuLabels
import majestic.payments.labels.SectionLabels

data class TransactionLabels(
    val emptyList: SectionLabels,
    val transactions: String,
    val menu: MenuLabels,
    val action: ActionLabels,
    val table: TableLabels
)
