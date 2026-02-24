package majestic.payments.invoice.table

import majestic.payments.tools.PaymentStatus
import org.jetbrains.compose.resources.DrawableResource

data class PaymentInvoice(
    val name: String,
    val info: String,
    val campus: String,
    val description: String,
    val reference: String,
    val date: String,
    val amount: String,
    val status: PaymentStatus,
    val avatar: DrawableResource
) {
    companion object Companion
}
