package majestic.payments.transaction.table

data class PaymentTransaction(
    val name: String,
    val payer: String,
    val purpose: String,
    val reference: String,
    val issued: String,
    val confirmed: String,
    val amount: String
) {
    companion object
}
