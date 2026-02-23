package majestic.payments.transaction.table

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cinematic.watchAsState
import composex.screen.orientation.Landscape
import majestic.Checkbox
import majestic.LazyTable
import majestic.payments.labels.transaction.TransactionLabels
import majestic.payments.tools.menu.MenuOption
import majestic.payments.tools.table.CommonCell
import majestic.payments.tools.table.TableColors
import majestic.payments.transaction.tools.TransactionMenuAction
import majestic.tooling.onClick
import majestic.tooling.separator
import symphony.Table

@Composable
fun TransactionTable(
    labels: TransactionLabels,
    colors: TableColors,
    table: Table<PaymentTransaction>,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val columns = table.columns.current.watchAsState()
    val weight = remember(columns) {
        buildMap {
            for (column in columns) {
                this[column] = when (column.key) {
                    labels.table.checkbox -> 2f
                    labels.table.name -> 5f
                    labels.table.payer -> 5f
                    labels.table.amount -> 5f
                    "" -> 2f
                    else -> 4f
                }
            }
        }
    }
    table.selector.selected.watchAsState()

    LazyTable(
        modifier = modifier,
        table = table,
        columns = { column ->
            val selectedAll = table.selector.isCurrentPageSelectedWholly()
            val checkboxColors =
                if (selectedAll) colors.checkbox.selected else colors.checkbox.unselected
            val header = colors.header

            if (column.key == labels.table.checkbox) Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.weight(weight.getValue(column))
                    .background(colors.headerBackground)
                    .separator(color = colors.separator)
                    .padding(vertical = 24.dp, horizontal = 12.dp),
            ) {
                Checkbox(
                    selected = table.selector.isCurrentPageSelectedWholly(),
                    colors = checkboxColors,
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier.size(16.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .background(checkboxColors.background)
                        .border(
                            width = 1.dp,
                            color = checkboxColors.border,
                            shape = RoundedCornerShape(5.dp)
                        )
                        .onClick { table.selector.toggleSelectionOfCurrentPage() }
                )
            } else Text(
                text = column.name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(weight.getValue(column))
                    .background(colors.headerBackground)
                    .separator(color = colors.separator)
                    .padding(vertical = 20.dp, horizontal = 12.dp),
                color = header.foreground.copy(0.6f)
            )
        }
    ) { cell ->
        val cellHeight = 70.dp
        val selected = table.selector.isRowSelectedOnCurrentPage(cell.row.number)

        when (cell.column.key) {
            labels.table.checkbox -> Box(
                modifier = Modifier.height(cellHeight)
                    .weight(weight.getValue(cell.column))
                    .background(if (selected) colors.hovered else Color.Transparent)
                    .separator(isLast = cell.row == table.rows.last(), color = colors.separator),
                contentAlignment = Alignment.Center
            ) {
                val checkboxColors =
                    if (selected) colors.checkbox.selected else colors.checkbox.unselected
                Checkbox(
                    selected = selected,
                    colors = checkboxColors,
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier.size(16.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .background(checkboxColors.background)
                        .border(1.dp, color = checkboxColors.border, RoundedCornerShape(5.dp))
                        .onClick {
                            if (selected) table.selector.unSelectRowInCurrentPage(row = cell.row.number)
                            else table.selector.addSelection(row = cell.row.number)
                        }
                )
            }

            labels.table.name -> CommonCell(
                modifier = Modifier.height(cellHeight)
                    .weight(weight.getValue(cell.column))
                    .background(if (selected) colors.hovered else Color.Transparent)
                    .separator(isLast = cell.row == table.rows.last(), color = colors.separator)
                    .pointerHoverIcon(PointerIcon.Hand)
                    .onClick(onClick)
                    .padding(horizontal = 12.dp),
                avatar = cell.row.item.avatar,
                title = cell.row.item.name,
                subtitle = cell.row.item.info,
                color = colors.foreground
            )

            labels.table.payer -> CommonCell(
                modifier = Modifier.height(cellHeight)
                    .weight(weight.getValue(cell.column))
                    .background(if (selected) colors.hovered else Color.Transparent)
                    .separator(isLast = cell.row == table.rows.last(), color = colors.separator)
                    .pointerHoverIcon(PointerIcon.Hand)
                    .onClick(onClick)
                    .padding(horizontal = 12.dp),
                avatar = cell.row.item.account,
                avatarShape = RoundedCornerShape(5.dp),
                title = cell.row.item.payer,
                subtitle = cell.row.item.phone,
                color = colors.foreground
            )

            labels.table.purpose -> CommonCell(
                modifier = Modifier.height(cellHeight)
                    .weight(weight.getValue(cell.column))
                    .background(if (selected) colors.hovered else Color.Transparent)
                    .separator(isLast = cell.row == table.rows.last(), color = colors.separator)
                    .pointerHoverIcon(PointerIcon.Hand)
                    .onClick(onClick)
                    .padding(horizontal = 12.dp),
                title = cell.row.item.purpose,
                subtitle = cell.row.item.date,
                color = colors.foreground
            )

            labels.table.reference -> CommonCell(
                modifier = Modifier.height(cellHeight)
                    .weight(weight.getValue(cell.column))
                    .background(if (selected) colors.hovered else Color.Transparent)
                    .separator(isLast = cell.row == table.rows.last(), color = colors.separator)
                    .pointerHoverIcon(PointerIcon.Hand)
                    .onClick(onClick)
                    .padding(horizontal = 12.dp),
                title = cell.row.item.reference,
                subtitle = cell.row.item.receipt,
                color = colors.foreground
            )

            labels.table.issued -> CommonCell(
                modifier = Modifier.height(cellHeight)
                    .weight(weight.getValue(cell.column))
                    .background(if (selected) colors.hovered else Color.Transparent)
                    .separator(isLast = cell.row == table.rows.last(), color = colors.separator)
                    .pointerHoverIcon(PointerIcon.Hand)
                    .onClick(onClick)
                    .padding(horizontal = 12.dp),
                title = cell.row.item.date,
                subtitle = cell.row.item.time,
                color = colors.foreground
            )

            labels.table.confirmed -> CommonCell(
                modifier = Modifier.height(cellHeight)
                    .weight(weight.getValue(cell.column))
                    .background(if (selected) colors.hovered else Color.Transparent)
                    .separator(isLast = cell.row == table.rows.last(), color = colors.separator)
                    .pointerHoverIcon(PointerIcon.Hand)
                    .onClick(onClick)
                    .padding(horizontal = 12.dp),
                title = cell.row.item.date,
                subtitle = cell.row.item.time,
                color = colors.foreground
            )

            labels.table.amount -> CommonCell(
                modifier = Modifier.height(cellHeight)
                    .weight(weight.getValue(cell.column))
                    .background(if (selected) colors.hovered else Color.Transparent)
                    .separator(isLast = cell.row == table.rows.last(), color = colors.separator)
                    .pointerHoverIcon(PointerIcon.Hand)
                    .onClick(onClick)
                    .padding(horizontal = 12.dp),
                avatar = cell.row.item.account,
                avatarShape = RoundedCornerShape(5.dp),
                title = cell.row.item.amountTitle,
                subtitle = "TZS ${cell.row.item.amount}",
                color = colors.foreground
            )

            "" -> Box(
                modifier = Modifier.height(cellHeight)
                    .weight(weight.getValue(cell.column))
                    .background(if (selected) colors.hovered else Color.Transparent)
                    .separator(isLast = cell.row == table.rows.last(), color = colors.separator)
                    .pointerHoverIcon(PointerIcon.Hand),
                contentAlignment = Alignment.Center
            ) {
                MenuOption(
                    colors = colors.menu,
                    orientation = Landscape,
                    actions = TransactionMenuAction.getMenus(labels.menu),
                    onAction = { /* TODO */ }
                )
            }
        }
    }
}
