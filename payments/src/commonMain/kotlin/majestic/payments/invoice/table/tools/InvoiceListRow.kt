package majestic.payments.invoice.table.tools

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import majestic.dropdown.Dropdown
import majestic.icons.Res
import majestic.icons.ic_more_vertical
import majestic.payments.invoice.table.PaymentInvoice
import majestic.payments.invoice.tools.InvoiceMenuAction
import majestic.payments.labels.invoice.InvoiceLabels
import majestic.payments.tools.table.TableColors
import majestic.shared.tools.dropdown.toDropdownItems
import nation.Country
import org.jetbrains.compose.resources.vectorResource

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
    Dropdown(
        items = InvoiceMenuAction.getMenus(labels.menu).toDropdownItems(),
        onAction = { /* TODO */ },
        colors = colors.dropdown,
        icon = vectorResource(Res.drawable.ic_more_vertical),
        isListItem = true
    )
}