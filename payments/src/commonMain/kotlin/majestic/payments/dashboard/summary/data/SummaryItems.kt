package majestic.payments.dashboard.summary.data

import androidx.compose.ui.graphics.Color
import majestic.payments.dashboard.summary.SummaryType

data class SummaryItems(
    val type: SummaryType,
    val value: String,
    val percentage: Float,
    val wallet: Int?,
    val iconColor: Color?
) {
    companion object {
        val items by lazy {
            listOf(
                SummaryItems(SummaryType.COLLECTED, "2,500,600", 2.3f, null, null),
                SummaryItems(SummaryType.UNPAID, "2,500,600", 8.5f, null, Color(0xFFE15B5B)),
                SummaryItems(SummaryType.EXPECTED, "20,060,230", 34f, null, null),
                SummaryItems(SummaryType.BALANCE, "50,060,230", 65f, 20, null)
            )
        }
    }
}
