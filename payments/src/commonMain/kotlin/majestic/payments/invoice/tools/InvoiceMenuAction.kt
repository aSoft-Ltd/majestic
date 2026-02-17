package majestic.payments.invoice.tools

import majestic.payments.labels.MenuLabels
import majestic.payments.tools.menu.OptionMenu

internal enum class InvoiceMenuAction {
    ViewInvoice,
    SendInvoice,
    Download,
    Share;

    fun getLabel(labels: MenuLabels): String = when (this) {
        ViewInvoice -> labels.viewInvoice
        SendInvoice -> labels.sendInvoice
        Download -> labels.download
        Share -> labels.share
    }

    companion object Companion {
        fun getMenus(labels: MenuLabels) = listOf(
            OptionMenu(
                label = ViewInvoice.getLabel(labels),
                action = ViewInvoice
            ),
            OptionMenu(
                label = SendInvoice.getLabel(labels),
                action = SendInvoice
            ),
            OptionMenu(
                label = Download.getLabel(labels),
                action = Download
            ),
            OptionMenu(
                label = Share.getLabel(labels),
                action = Share
            ),
        )
    }
}
