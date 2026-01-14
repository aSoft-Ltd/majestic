package majestic.payments.transaction.tools.data

import majestic.payments.tools.ActionDropdownItem
import org.jetbrains.compose.resources.DrawableResource
import tz.co.asoft.majestic_payments.generated.resources.Res
import tz.co.asoft.majestic_payments.generated.resources.ic_apple_reminder

internal enum class TransactionReview {
    REMINDED;

    val icon: DrawableResource
        get() = when (this) {
            REMINDED -> Res.drawable.ic_apple_reminder
        }

    val label: String
        get() = when (this) {
            REMINDED -> "Reminded"
        }

    companion object {
        fun getActions() = listOf(
            ActionDropdownItem(
                label = REMINDED.label,
                icon = Res.drawable.ic_apple_reminder
            ),
        )
    }
}
