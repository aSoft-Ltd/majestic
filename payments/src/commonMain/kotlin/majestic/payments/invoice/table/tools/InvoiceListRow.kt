package majestic.payments.invoice.table.tools

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Portrait
import majestic.payments.invoice.table.PaymentInvoice
import majestic.payments.invoice.tools.InvoiceMenuAction
import majestic.payments.labels.invoice.InvoiceLabels
import majestic.payments.tools.menu.MenuOption
import majestic.payments.tools.table.TableColors
import nation.Country

@Composable
fun InvoiceListRow(
    labels: InvoiceLabels,
    colors: TableColors,
    details: PaymentInvoice,
    modifier: Modifier = Modifier
) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(12.dp)
) {
    InvoiceItem(
        modifier = Modifier.weight(1f),
        labels = labels.status,
        name = details.name,
        reference = details.reference,
        amount = details.amount,
        status = details.status,
        country = Country.TZ,
        avatar = details.avatar,
        color = colors.foreground
    )
    MenuOption(
        colors = colors.menu,
        orientation = Portrait,
        actions = InvoiceMenuAction.getMenus(labels.menu),
        onAction = { /* TODO */ }
    )
}
