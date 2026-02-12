package majestic.payments.wallet.details.analytics.tools

internal enum class TransactionFilter {
    SPLINT, COMBINE;

    fun getLabel(): String {
        return when (this) {
            SPLINT -> "Splint"
            COMBINE -> "Combine"
        }
    }
}
