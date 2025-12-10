package majestic.payments.labels

data class WalletLabels(
    val emptyList: SectionLabels
) {
    companion object {
        val english by lazy {
            WalletLabels(
                emptyList = SectionLabels(
                    label = "Payment Wallet",
                    description = "There are no payment wallets yet. Create a new wallet by clicking the add button."
                )
            )
        }

        val swahili by lazy {
            WalletLabels(
                emptyList = SectionLabels(
                    label = "Kifuko cha Malipo",
                    description = "Hakuna mifuko ya malipo bado. Unda kifuko kipya kwa kubofya kitufe cha kuongeza."
                )
            )
        }
    }
}
