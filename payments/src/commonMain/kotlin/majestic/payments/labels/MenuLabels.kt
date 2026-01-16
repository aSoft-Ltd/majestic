package majestic.payments.labels

data class MenuLabels(
    val view: String,
    val edit: String,
    val close: String,
    val delete: String,
    val reversal: String,
    val viewReceipt: String,
    val shareReceipt: String,
    val downloadReceipt: String,
) {
    companion object {
        val english: MenuLabels by lazy {
            MenuLabels(
                view = "View",
                edit = "Edit",
                close = "Close",
                delete = "Delete",
                reversal = "Reversal",
                viewReceipt = "View Receipt",
                shareReceipt = "Share Receipt",
                downloadReceipt = "Download Receipt"
            )
        }

        val swahili: MenuLabels by lazy {
            MenuLabels(
                view = "Tazama",
                edit = "Hariri",
                close = "Funga",
                delete = "Futa",
                reversal = "Rudisha",
                viewReceipt = "Tazama Risiti",
                shareReceipt = "Sambaza Risiti",
                downloadReceipt = "Pakua Risiti"
            )
        }
    }
}
