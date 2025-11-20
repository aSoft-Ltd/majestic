package majestic.payments.dashboard.summary

import majestic.payments.tools.labels.SummaryLabels
import org.jetbrains.compose.resources.DrawableResource
import tz.co.asoft.majestic_payments.generated.resources.Res
import tz.co.asoft.majestic_payments.generated.resources.ic_coins_01
import tz.co.asoft.majestic_payments.generated.resources.ic_money_bag_02
import tz.co.asoft.majestic_payments.generated.resources.ic_tips
import tz.co.asoft.majestic_payments.generated.resources.ic_wallet_01

enum class SummaryType(val icon: DrawableResource) {
    COLLECTED(Res.drawable.ic_money_bag_02),
    UNPAID(Res.drawable.ic_coins_01),
    EXPECTED(Res.drawable.ic_tips),
    BALANCE(Res.drawable.ic_wallet_01);

    fun getLabel(labels: SummaryLabels): String {
        return when (this) {
            COLLECTED -> labels.collected
            UNPAID -> labels.unpaid
            EXPECTED -> labels.expected
            BALANCE -> labels.balance
        }
    }
}
