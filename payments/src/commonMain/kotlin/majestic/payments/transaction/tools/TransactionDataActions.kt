package majestic.payments.transaction.tools

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import majestic.ColorPair
import majestic.payments.labels.ActionLabels
import majestic.payments.tools.ActionDropdown
import majestic.payments.tools.ActionDropdownItem
import majestic.payments.tools.filters.FilterByYear
import majestic.payments.tools.filters.FilterDefault
import majestic.payments.transaction.tools.data.TransactionProcess
import majestic.payments.transaction.tools.data.TransactionReview
import tz.co.asoft.majestic_payments.generated.resources.Res
import tz.co.asoft.majestic_payments.generated.resources.ic_book_open_01
import tz.co.asoft.majestic_payments.generated.resources.ic_cursor_rectangle_selection_02

@Composable
fun TransactionDataActions(
    labels: ActionLabels,
    colors: ColorPair,
    filterDefault: FilterDefault,
    modifier: Modifier = Modifier,
) = Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
    ActionDropdown(
        modifier = Modifier.width(IntrinsicSize.Max),
        colors = colors,
        actions = TransactionProcess.getActions(labels),
        placeholder = ActionDropdownItem(
            label = labels.process,
            icon = Res.drawable.ic_cursor_rectangle_selection_02
        ),
    )
    ActionDropdown(
        modifier = Modifier.width(IntrinsicSize.Max),
        colors = colors,
        actions = TransactionReview.getActions(labels),
        placeholder = ActionDropdownItem(
            label = labels.review,
            icon = Res.drawable.ic_book_open_01
        ),
    )
    FilterByYear(
        years = listOf("2025", "2024", "2023"),
        selected = "2025",
        modifier = Modifier.width(110.dp),
        defaults = filterDefault
    )
}
