package majestic.payments.labels

data class DashboardLabels(
    val wallet: String,
    val transaction: String,
    val btnManage: String,
) {
    internal companion object {
        val english by lazy {
            DashboardLabels(
                wallet = "Wallet",
                transaction = "Transactions",
                btnManage = "Manage"
            )
        }

        val swahili by lazy {
            DashboardLabels(
                wallet = "Pochi",
                transaction = "Miamala",
                btnManage = "Simamia"
            )
        }
    }
}
