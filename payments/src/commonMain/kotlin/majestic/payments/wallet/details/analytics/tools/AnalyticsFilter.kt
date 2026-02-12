package majestic.payments.wallet.details.analytics.tools

internal enum class AnalyticsFilter {
    ACCOUNT, ITEM;

    fun getLabel(): String {
        return when (this) {
            ACCOUNT -> "Per Account"
            ITEM -> "Per Class"
        }
    }
}
