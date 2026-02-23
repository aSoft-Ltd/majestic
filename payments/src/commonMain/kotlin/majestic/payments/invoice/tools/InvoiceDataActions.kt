package majestic.payments.invoice.tools

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import majestic.dropdown.Dropdown
import majestic.dropdown.DropdownColors
import majestic.payments.invoice.tools.data.InvoiceProcess
import majestic.payments.invoice.tools.data.InvoiceReview
import majestic.payments.labels.ActionLabels
import majestic.payments.tools.dropdown.toDropdownItems
import majestic.payments.tools.filters.FilterByYear
import org.jetbrains.compose.resources.vectorResource
import tz.co.asoft.majestic_payments.generated.resources.Res
import tz.co.asoft.majestic_payments.generated.resources.ic_book_open_01
import tz.co.asoft.majestic_payments.generated.resources.ic_cursor_rectangle_selection_02

@Composable
fun InvoiceDataActions(
    labels: ActionLabels,
    colors: DropdownColors,
    modifier: Modifier = Modifier,
) = Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
    Dropdown(
        items = InvoiceProcess.getActions(labels).toDropdownItems(),
        onAction = { /* TODO */ },
        colors = colors,
        label = "Process",
        leadingIcon = vectorResource(Res.drawable.ic_cursor_rectangle_selection_02),
        modifier = Modifier.width(IntrinsicSize.Max)
    )
    Dropdown(
        items = InvoiceReview.getActions(labels).toDropdownItems(),
        onAction = { /* TODO */ },
        colors = colors,
        label = "Review",
        leadingIcon = vectorResource(Res.drawable.ic_book_open_01),
        modifier = Modifier.width(IntrinsicSize.Max)
    )
    FilterByYear(
        years = listOf("2025", "2024", "2023"),
        selected = "2025",
        colors = colors
    )
}