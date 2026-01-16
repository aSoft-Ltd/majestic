package majestic.payments.transaction.table

import org.jetbrains.compose.resources.DrawableResource

data class PaymentTransaction(
    val name: String,
    val info: String,
    val payer: String,
    val purpose: String,
    val reference: String,
    val receipt: String,
    val date: String,
    val time: String,
    val phone: String,
    val amount: String,
    val amountTitle: String,
    val avatar: DrawableResource,
    val account: DrawableResource
) {
    companion object
}
