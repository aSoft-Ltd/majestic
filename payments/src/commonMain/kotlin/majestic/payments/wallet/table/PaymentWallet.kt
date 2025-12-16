package majestic.payments.wallet.table

data class PaymentWallet(
    val id: String,
    val name: String,
    val balance: Double
) {
    companion object
}
