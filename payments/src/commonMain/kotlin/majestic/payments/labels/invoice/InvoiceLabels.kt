package majestic.payments.labels.invoice

import majestic.payments.labels.ActionLabels
import majestic.payments.labels.MenuLabels
import majestic.payments.labels.PaymentStatusLabels
import majestic.payments.labels.SectionLabels

class InvoiceLabels(
    val emptyList: SectionLabels,
    val invoices: String,
    val address: String,
    val invoiceNo: String,
    val issued: String,
    val due: String,
    val number: String,
    val item: String,
    val rate: String,
    val qty: String,
    val tax: String,
    val disc: String,
    val total: String,
    val subTotal: String,
    val menu: MenuLabels,
    val action: ActionLabels,
    val table: TableLabels,
    val status: PaymentStatusLabels,
    val detail: DetailTabLabels
)
