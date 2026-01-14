package majestic.payments.transaction.tools.data

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

    val label: String
        get() = when (this) {
            EXPORT -> "Exports"
            REMINDER -> "Reminders"
        }

    companion object {
        fun getActions() = listOf(
            ActionDropdownItem(
                label = EXPORT.label,
                icon = Res.drawable.ic_share_05
            ),
            ActionDropdownItem(
                label = REMINDER.label,
                icon = Res.drawable.ic_apple_reminder
            ),
        )
    }
}
