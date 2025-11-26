package majestic.payments.tools

enum class DateRangeOption(val label: String) {
    THIS_MONTH("This Month"),
    PAST_90_DAYS("Past 90 days"),
    THIS_YEAR("This Year"),
    PAST_YEAR("Past Year"),
    CUSTOM("Custom Range");

    companion object
}
