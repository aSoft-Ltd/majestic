package majestic.payments.wallet.card

import org.jetbrains.compose.resources.DrawableResource

data class WalletDetail(
    val name: String,
    val amount: String,
    val accounts: List<DrawableResource>,
    val transactions: List<DrawableResource>,
) {
    companion object
}
