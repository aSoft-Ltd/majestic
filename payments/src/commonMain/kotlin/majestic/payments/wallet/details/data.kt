package majestic.payments.wallet.details

import majestic.payments.tools.header.InfoEntryItem
import tz.co.asoft.majestic_payments.generated.resources.Res
import tz.co.asoft.majestic_payments.generated.resources.ic_calendar_04
import tz.co.asoft.majestic_payments.generated.resources.ic_sticky_note_02
import tz.co.asoft.majestic_payments.generated.resources.ic_touchpad_04

internal fun InfoEntryItem.Companion.wallet() = listOf(
    InfoEntryItem(
        icon = Res.drawable.ic_calendar_04,
        title = "12/10/2025",
        description = "Created On",
    ),
    InfoEntryItem(
        icon = Res.drawable.ic_touchpad_04,
        title = "04",
        description = "Number of Accounts",
    ),
    InfoEntryItem(
        icon = Res.drawable.ic_sticky_note_02,
        title = "210",
        description = "Transactions",
    ),
    InfoEntryItem(
        icon = Res.drawable.ic_sticky_note_02,
        title = "TZS 2,100,000/=",
        description = "Created On",
    )
)
