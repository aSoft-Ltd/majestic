package majestic.payments.invoice.detail.tools

class InvoiceDetail(
    val address: String,
    val invoiceNo: String,
    val issued: String,
    val due: String,
    val items: List<InvoiceItem>,
    val discount: String,
    val tax: String,
    val subTotal: String,
    val total: String,
) {
    companion object
}
