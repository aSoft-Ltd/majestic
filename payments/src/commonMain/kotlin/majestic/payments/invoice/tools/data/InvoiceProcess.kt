package majestic.payments.invoice.tools.data

import majestic.payments.labels.ActionLabels
import majestic.payments.tools.menu.OptionMenu
import org.jetbrains.compose.resources.DrawableResource
import tz.co.asoft.majestic_payments.generated.resources.Res
import tz.co.asoft.majestic_payments.generated.resources.ic_apple_reminder
import tz.co.asoft.majestic_payments.generated.resources.ic_share_05

internal enum class InvoiceProcess {
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

    companion object Companion {
        fun getActions(labels: ActionLabels) = listOf(
            OptionMenu(
                label = EXPORT.getLabel(labels),
                action = labels,
                icon = Res.drawable.ic_share_05
            ),
            OptionMenu(
                label = REMINDER.getLabel(labels),
                action = labels,
                icon = Res.drawable.ic_apple_reminder
            ),
        )
    }
}