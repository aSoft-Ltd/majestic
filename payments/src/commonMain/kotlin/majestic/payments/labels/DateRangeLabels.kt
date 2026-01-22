package majestic.payments.labels

data class DateRangeLabels(
    val thisMonth: String,
    val past90Days: String,
    val thisYear: String,
    val pastYear: String,
    val custom: String
)
