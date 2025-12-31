package majestic.payments.labels

data class WalletLabels(
    val emptyList: SectionLabels,
    val transactions: String,
    val menu: MenuLabels,
    val table: TableLabels
) {
    companion object {
        val english by lazy {
            WalletLabels(
                emptyList = SectionLabels(
                    label = "Payment Wallet",
                    description = "There are no payment wallets yet. Create a new wallet by clicking the add button."
                ),
                transactions = "Transactions",
                menu = MenuLabels.english,
                table = TableLabels.english
            )
        }

        val swahili by lazy {
            WalletLabels(
                emptyList = SectionLabels(
                    label = "Kifuko cha Malipo",
                    description = "Hakuna mifuko ya malipo bado. Unda kifuko kipya kwa kubofya kitufe cha kuongeza."
                ),
                transactions = "Miamala",
                menu = MenuLabels.swahili,
                table = TableLabels.swahili
            )
        }
    }
}
