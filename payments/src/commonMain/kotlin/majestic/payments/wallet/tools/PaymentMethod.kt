package majestic.payments.wallet.tools

import org.jetbrains.compose.resources.DrawableResource

data class PaymentMethod(
    val name: String,
    val account: String,
    val type: String,
    val image: DrawableResource,
) {
    companion object
}
