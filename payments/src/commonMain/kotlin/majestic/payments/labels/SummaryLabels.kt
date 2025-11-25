package majestic.payments.labels

data class SummaryLabels(
    val collected: String,
    val unpaid: String,
    val expected: String,
    val balance: String,
    val wallet: String,
    val view: String
) {
    companion object {
        val english by lazy {
            SummaryLabels(
                collected = "Collected",
                unpaid = "Unpaid",
                expected = "Expected",
                balance = "Balance",
                wallet = "Wallet",
                view = "View List"
            )
        }

        val swahili by lazy {
            SummaryLabels(
                collected = "Imekusanywa",
                unpaid = "Haijalipwa",
                expected = "Inayotarajiwa",
                balance = "Salio",
                wallet = "Pochi",
                view = "Orodha"
            )
        }
    }
}
