package majestic.payments.labels.transaction

data class ActionLabels(
    val process: String,
    val review: String,
    val export: String,
    val reminder: String,
    val reminded: String
)
