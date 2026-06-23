package majestic.shared.credit.tools

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.Cell
import majestic.dropdown.Dropdown
import majestic.icons.Res
import majestic.icons.ic_more_horizontal
import majestic.shared.credit.CreditUsage
import majestic.shared.credit.tools.cells.CreditActorCell
import majestic.shared.credit.tools.cells.CreditPurchasedCell
import majestic.shared.credit.tools.cells.CreditReferenceCell
import majestic.shared.tools.dropdown.toDropdownItems
import majestic.shared.tools.menu.OptionMenu
import majestic.tooling.separator
import org.jetbrains.compose.resources.vectorResource
import symphony.columns.Column

@Composable
fun <T> RowScope.CreditRow(
    credit: CreditUsage,
    cell: Cell<CreditUsage>,
    weights: Map<Column<CreditUsage>, Float>,
    colors: CreditTableColors,
    actions: List<OptionMenu<T>>,
    cellHeight: Dp = 70.dp,
    isLast: Boolean = false,
) = when (cell.column.index) {
    0 -> CreditActorCell(
        logo = credit.recipientLogo,
        name = credit.recipientName,
        id = credit.recipientId,
        colors = colors,
        modifier = Modifier
            .weight(weights.getValue(cell.column))
            .height(cellHeight)
            .background(colors.body)
            .separator(isLast = isLast, color = colors.separator)
            .padding(horizontal = 12.dp)
    )

    1 -> CreditActorCell(
        logo = credit.payerLogo,
        name = credit.payerName,
        id = credit.payerId,
        colors = colors,
        modifier = Modifier
            .weight(weights.getValue(cell.column))
            .height(cellHeight)
            .background(colors.body)
            .separator(isLast = isLast, color = colors.separator)
            .padding(horizontal = 12.dp)
    )

    2 -> CreditReferenceCell(
        invoiceRef = credit.invoiceRef,
        txnRef = credit.txnRef,
        colors = colors,
        modifier = Modifier
            .weight(weights.getValue(cell.column))
            .height(cellHeight)
            .background(colors.body)
            .separator(isLast = isLast, color = colors.separator)
            .padding(horizontal = 12.dp)
    )

    3 -> CreditPurchasedCell(
        credit = credit,
        colors = colors,
        modifier = Modifier
            .weight(weights.getValue(cell.column))
            .height(cellHeight)
            .background(colors.body)
            .separator(isLast = isLast, color = colors.separator)
            .padding(horizontal = 12.dp)
    )

    4 -> Box(
        contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .weight(weights.getValue(cell.column))
            .height(cellHeight)
            .background(colors.body)
            .separator(isLast = isLast, color = colors.separator)
            .padding(horizontal = 12.dp)
    ) {
        Text(
            text = credit.amount,
            color = colors.surfaceColor.foreground,
            fontSize = 15.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }

    5 -> Box(
        contentAlignment = Alignment.CenterEnd,
        modifier = Modifier
            .weight(weights.getValue(cell.column))
            .height(cellHeight)
            .background(colors.body)
            .separator(isLast = isLast, color = colors.separator)
            .padding(horizontal = 12.dp)
    ) {
        Dropdown(
            items = actions.toDropdownItems(),
            onAction = {},
            isListItem = true,
            colors = colors.dropdownColors,
            icon = vectorResource(Res.drawable.ic_more_horizontal)
        )
    }

    else -> Unit
}