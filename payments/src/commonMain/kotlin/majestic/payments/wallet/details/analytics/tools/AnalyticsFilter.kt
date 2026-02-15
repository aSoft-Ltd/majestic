package majestic.payments.wallet.details.analytics.tools

import majestic.payments.labels.wallet.AnalyticsLabels

internal enum class AnalyticsFilter {
    ACCOUNT, ITEM;

    fun getDescription(labels: AnalyticsLabels): String {
        return when (this) {
            ACCOUNT -> labels.perAccount
            ITEM -> labels.perItem
        }
    }
}
