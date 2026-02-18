package majestic.payments.invoice.tools.data

import majestic.payments.labels.ActionLabels
import majestic.payments.tools.ActionDropdownItem
import org.jetbrains.compose.resources.DrawableResource
import tz.co.asoft.majestic_payments.generated.resources.Res
import tz.co.asoft.majestic_payments.generated.resources.ic_apple_reminder

internal enum class InvoiceReview {
    REMINDED;

    val icon: DrawableResource
        get() = when (this) {
            REMINDED -> Res.drawable.ic_apple_reminder
        }

    fun getLabel(labels: ActionLabels): String = when (this) {
        REMINDED -> labels.reminded
    }

    companion object Companion {
        fun getActions(labels: ActionLabels) = listOf(
            ActionDropdownItem(
                label = REMINDED.getLabel(labels),
                icon = Res.drawable.ic_apple_reminder
            ),
        )
    }
}
