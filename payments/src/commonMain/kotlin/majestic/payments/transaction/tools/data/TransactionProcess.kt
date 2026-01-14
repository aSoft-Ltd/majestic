package majestic.payments.transaction.tools.data

import majestic.payments.labels.transaction.ActionLabels
import majestic.payments.tools.ActionDropdownItem
import org.jetbrains.compose.resources.DrawableResource
import tz.co.asoft.majestic_payments.generated.resources.Res
import tz.co.asoft.majestic_payments.generated.resources.ic_apple_reminder
import tz.co.asoft.majestic_payments.generated.resources.ic_share_05

internal enum class TransactionProcess {
    EXPORT, REMINDER;

    val icon: DrawableResource
        get() = when (this) {
            EXPORT -> Res.drawable.ic_share_05
            REMINDER -> Res.drawable.ic_apple_reminder
        }

    fun getLabel(labels: ActionLabels): String = when (this) {
        EXPORT -> labels.export
        REMINDER -> labels.reminder
    }

    companion object {
        fun getActions(labels: ActionLabels) = listOf(
            ActionDropdownItem(
                label = EXPORT.getLabel(labels),
                icon = Res.drawable.ic_share_05
            ),
            ActionDropdownItem(
                label = REMINDER.getLabel(labels),
                icon = Res.drawable.ic_apple_reminder
            ),
        )
    }
}
