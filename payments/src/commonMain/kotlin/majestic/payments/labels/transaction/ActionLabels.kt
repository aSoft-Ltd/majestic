package majestic.payments.labels.transaction

data class ActionLabels(
    val process: String,
    val review: String,
    val export: String,
    val reminder: String,
    val reminded: String
) {
    companion object {
        val english: ActionLabels by lazy {
            ActionLabels(
                process = "Process",
                review = "Review",
                export = "Export",
                reminder = "Reminder",
                reminded = "Reminded"
            )
        }

        val swahili: ActionLabels by lazy {
            ActionLabels(
                process = "Endesha",
                review = "Kagua",
                export = "Hamisha",
                reminder = "Kikumbusho",
                reminded = "Kikumbusho kimetumwa"
            )
        }
    }
}
