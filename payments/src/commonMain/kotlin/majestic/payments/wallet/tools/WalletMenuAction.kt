package majestic.payments.wallet.tools

import majestic.payments.labels.MenuLabels
import majestic.shared.menu.OptionMenu

internal enum class WalletMenuAction {
    View,
    Edit,
    Close,
    Delete;

    fun getLabel(labels: MenuLabels): String = when (this) {
        View -> labels.view
        Edit -> labels.edit
        Close -> labels.close
        Delete -> labels.delete
    }

    companion object {
        fun getMenus(labels: MenuLabels) = listOf(
            OptionMenu(
                label = View.getLabel(labels),
                action = View
            ),
            OptionMenu(
                label = Edit.getLabel(labels),
                action = Edit
            ),
            OptionMenu(
                label = Close.getLabel(labels),
                action = Close
            ),
            OptionMenu(
                label = Delete.getLabel(labels),
                action = Delete,
                isDestructive = true
            ),
        )
    }
}