package majestic.payments.wallet.table

import org.jetbrains.compose.resources.DrawableResource

data class PaymentWallet(
    val name: String,
    val amount: String,
    val accounts: List<DrawableResource>,
    val transactions: List<DrawableResource>,
    val createdAt: String,
    val createdBy: String,
    val recent: Recent
) {
    data class Recent(
        val name: String?,
        val amount: String,
        val person: String,
        val avatar: DrawableResource
    )

    companion object
}
