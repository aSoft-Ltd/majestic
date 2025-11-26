package majestic.payments.labels

data class DateRangeLabels(
    val thisMonth: String,
    val past90Days: String,
    val thisYear: String,
    val pastYear: String,
    val custom: String
) {
    companion object {
        val english by lazy {
            DateRangeLabels(
                thisMonth = "This Month",
                past90Days = "Past 90 days",
                thisYear = "This Year",
                pastYear = "Past Year",
                custom = "Custom Range",
            )
        }

        val swahili by lazy {
            DateRangeLabels(
                thisMonth = "Mwezi huu",
                past90Days = "Siku 90 zilizopita",
                thisYear = "Mwaka huu",
                pastYear = "Mwaka uliopita",
                custom = "Kipindi maalum",
            )
        }
    }
}
