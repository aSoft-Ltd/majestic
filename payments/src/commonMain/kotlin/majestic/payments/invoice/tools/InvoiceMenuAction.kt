package majestic.payments.invoice.tools

import majestic.payments.labels.MenuLabels
import majestic.shared.tools.menu.OptionMenu

internal enum class InvoiceMenuAction {
    View,
    Send,
    Share,
    Download;

    fun getLabel(labels: MenuLabels): String = when (this) {
        View -> labels.viewInvoice
        Send -> labels.sendInvoice
        Share -> labels.share
        Download -> labels.download
    }

    companion object Companion {
        fun getMenus(labels: MenuLabels) = listOf(
            OptionMenu(
                label = View.getLabel(labels),
                action = View
            ),
            OptionMenu(
                label = Send.getLabel(labels),
                action = Send
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