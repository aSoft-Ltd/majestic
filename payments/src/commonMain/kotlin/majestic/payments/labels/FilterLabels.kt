package majestic.payments.labels

data class FilterLabels(
    val dateRange: DateRangeLabels
) {
    companion object {
        val english by lazy {
            FilterLabels(
                dateRange = DateRangeLabels.english
            )
        }

        val swahili by lazy {
            FilterLabels(
                dateRange = DateRangeLabels.swahili
            )
        }
    }
}
