package majestic.payments.labels.wallet

data class TableLabels(
    val checkbox: String,
    val wallet: String,
    val transactions: String,
    val accounts: String,
    val created: String,
    val recent: String,
    val amount: String,
    val noPayment: String
) {
    internal companion object {
        val english by lazy {
            TableLabels(
                checkbox = "Checkbox",
                wallet = "Wallet",
                transactions = "Transactions",
                accounts = "Accounts",
                created = "Created",
                recent = "Recent",
                amount = "Amount",
                noPayment = "No payment received"
            )
        }

        val swahili by lazy {
            TableLabels(
                checkbox = "Checkbox",
                wallet = "Pochi",
                transactions = "Miamala",
                accounts = "Akaunti",
                created = "Imeundwa",
                recent = "Hivi karibuni",
                amount = "Kiasi",
                noPayment = "Hakuna malipo yaliyopokelewa"
            )
        }
    }
}
