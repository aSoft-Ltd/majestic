package majestic.payments.wallet.tools

import majestic.payments.labels.MenuLabels
import majestic.payments.tools.menu.OptionMenu

internal enum class WalletDetailMenuAction {
    ShareReceipt,
    DownloadReceipt,
    Reversal;

    fun getLabel(labels: MenuLabels): String = when (this) {
        ShareReceipt -> labels.shareReceipt
        DownloadReceipt -> labels.downloadReceipt
        Reversal -> labels.reversal
    }

    companion object {
        fun getMenus(labels: MenuLabels) = listOf(
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
            )
        )
    }
}
