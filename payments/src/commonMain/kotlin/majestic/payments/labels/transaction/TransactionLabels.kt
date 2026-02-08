package majestic.payments.labels.transaction

import majestic.payments.labels.MenuLabels
import majestic.payments.labels.SectionLabels

class TransactionLabels(
    val emptyList: SectionLabels,
    val transactions: String,
    val payerInfo: String,
    val recipientInfo: String,
    val accountName: String,
    val accountNo: String,
    val purpose: String,
    val proof: String,
    val bank: String,
    val amount: String,
    val menu: MenuLabels,
    val action: ActionLabels,
    val table: TableLabels,
    val detail: DetailTabLabels,
    val infoEntry: InfoEntryLabels
)
