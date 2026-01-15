package majestic.payments.labels.transaction

data class TableLabels(
    val checkbox: String,
    val studentName: String,
    val payer: String,
    val reference: String,
    val issued: String,
    val confirmed: String,
    val amount: String,
) {
    internal companion object {
        val english by lazy {
            TableLabels(
                checkbox = "Checkbox",
                studentName = "Student Name",
                payer = "Payer",
                reference = "Reference",
                issued = "Issued",
                confirmed = "Confirmed",
                amount = "Amount"
            )
        }

        val swahili by lazy {
            TableLabels(
                checkbox = "Checkbox",
                studentName = "Jina la Mwanafunzi",
                payer = "Mlipa",
                reference = "Rejea",
                issued = "Imetolewa",
                confirmed = "Imethibitishwa",
                amount = "Kiasi"
            )
        }
    }
}
