package majestic.payments.transaction.table.tools

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Portrait
import majestic.payments.labels.transaction.TransactionLabels
import majestic.payments.tools.menu.MenuOption
import majestic.payments.tools.table.TableColors
import majestic.payments.transaction.table.PaymentTransaction
import majestic.payments.transaction.tools.TransactionMenuAction

@Composable
fun TransactionListRow(
    labels: TransactionLabels,
    colors: TableColors,
    details: PaymentTransaction,
    modifier: Modifier = Modifier
) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(12.dp)
) {
    PhotoBadge(
        recipient = details.account,
        payer = details.account
    )
    TransactionItem(
        modifier = Modifier.weight(1f),
        name = details.payer,
        code = details.reference,
        account = details.amountTitle,
        amount = details.amount,
        avatar = details.avatar,
        color = colors.foreground
    )
    MenuOption(
        colors = colors.menu,
        orientation = Portrait,
        actions = TransactionMenuAction.getMenus(labels.menu),
        onAction = { /* TODO */ }
    )
}
