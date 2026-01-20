package majestic.payments.labels.transaction

data class TableLabels(
    val checkbox: String,
    val name: String,
    val payer: String,
    val purpose: String,
    val reference: String,
    val issued: String,
    val confirmed: String,
    val amount: String,
) {
    internal companion object {
        val english by lazy {
            TableLabels(
                checkbox = "Checkbox",
                name = "Name",
                payer = "Payer",
                purpose = "Purpose",
                reference = "Reference",
                issued = "Issued",
                confirmed = "Confirmed",
                amount = "Amount"
            )
        }

        val swahili by lazy {
            TableLabels(
                checkbox = "Checkbox",
                name = "Jina",
                payer = "Mlipa",
                purpose = "Madhumuni",
                reference = "Rejea",
                issued = "Imetolewa",
                confirmed = "Imethibitishwa",
                amount = "Kiasi"
            )
        }
    }
}
