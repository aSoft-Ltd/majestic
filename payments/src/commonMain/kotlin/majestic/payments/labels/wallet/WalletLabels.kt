package majestic.payments.labels.wallet

import majestic.payments.labels.MenuLabels
import majestic.payments.labels.SectionLabels
import majestic.payments.labels.wallet.form.NewWalletLabels

data class WalletLabels(
    val emptyList: SectionLabels,
    val transactions: String,
    val menu: MenuLabels,
    val table: TableLabels,
    val form: NewWalletLabels,
    val detail: DetailTabLabels,
    val infoEntry: InfoEntryLabels
)
