package majestic.payments.transaction.tools

import majestic.payments.labels.MenuLabels
import majestic.shared.menu.OptionMenu

internal enum class TransactionMenuAction {
    ViewReceipt,
    ShareReceipt,
    DownloadReceipt,
    Reversal;

    fun getLabel(labels: MenuLabels): String = when (this) {
        ViewReceipt -> labels.viewReceipt
        ShareReceipt -> labels.shareReceipt
        DownloadReceipt -> labels.downloadReceipt
        Reversal -> labels.reversal
    }

    companion object {
        fun getMenus(labels: MenuLabels) = listOf(
            OptionMenu(
                label = ViewReceipt.getLabel(labels),
                action = ViewReceipt
            ),
            OptionMenu(
                label = ShareReceipt.getLabel(labels),
                action = ShareReceipt
            ),
            OptionMenu(
                label = DownloadReceipt.getLabel(labels),
                action = DownloadReceipt
            ),
            OptionMenu(
                label = Reversal.getLabel(labels),
                action = Reversal
            ),
        )
    }
}