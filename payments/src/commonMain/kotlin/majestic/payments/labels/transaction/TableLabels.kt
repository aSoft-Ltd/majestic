package majestic.payments.labels.transaction

data class TableLabels(
    val checkbox: String,
    val name: String,
    val payer: String,
    val purpose: String,
    val reference: String,
    val issued: String,
    val confirmed: String,
    val amount: String
)
