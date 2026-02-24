package majestic.payments.transaction.tools.data

import majestic.payments.labels.ActionLabels
import majestic.shared.tools.menu.OptionMenu
import org.jetbrains.compose.resources.DrawableResource
import tz.co.asoft.majestic_payments.generated.resources.Res
import tz.co.asoft.majestic_payments.generated.resources.ic_apple_reminder

internal enum class TransactionReview {
    REMINDED;

    val icon: DrawableResource
        get() = when (this) {
            REMINDED -> Res.drawable.ic_apple_reminder
        }

    fun getLabel(labels: ActionLabels): String = when (this) {
        REMINDED -> labels.reminded
    }

    companion object {
        fun getActions(labels: ActionLabels) = listOf(
            OptionMenu(
                label = REMINDED.getLabel(labels),
                action = labels,
                icon = Res.drawable.ic_apple_reminder
            ),
        )
    }
}