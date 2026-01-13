package majestic.payments.wallet.table

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.unit.sp
import cinematic.watchAsState
import composex.screen.orientation.Landscape
import majestic.Checkbox
import majestic.CheckboxColors
import majestic.ColorPair
import majestic.LazyTable
import majestic.payments.labels.WalletLabels
import majestic.payments.tools.menu.MenuOption
import majestic.payments.tools.menu.MenuOptionColors
import majestic.payments.tools.separator
import majestic.payments.wallet.table.tools.CreatedCell
import majestic.payments.wallet.table.tools.NameCell
import majestic.payments.wallet.table.tools.RecentCell
import majestic.payments.wallet.tools.Avatar
import majestic.payments.wallet.tools.AvatarOverflow
import majestic.payments.wallet.tools.WalletMenuAction
import majestic.tooling.onClick
import symphony.Table
import tz.co.asoft.majestic_payments.generated.resources.Res
import tz.co.asoft.majestic_payments.generated.resources.user_avatar

data class TableColors(
    val background: Color,
    val headerBackground: Color,
    val foreground: Color,
    val separator: Color,
    val hovered: Color,
    val icon: Color,
    val header: ColorPair,
    val checkbox: CheckboxColors,
    val menu: MenuOptionColors
)

@Composable
fun WalletTable(
    labels: WalletLabels,
    colors: TableColors,
    table: Table<PaymentWallet>,
    modifier: Modifier = Modifier,
) {
    val columns = table.columns.current.watchAsState()
    val weight = remember(columns) {
        buildMap {
            for (column in columns) {
                this[column] = when (column.key) {
                    labels.table.checkbox -> 2f
                    labels.table.wallet -> 5f
                    labels.table.transactions -> 5f
                    labels.table.recent -> 7f
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
            val checkboxColors = if (selectedAll) colors.checkbox.selected else colors.checkbox.unselected
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
                        .border(1.dp, color = checkboxColors.border, RoundedCornerShape(5.dp))
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
                val checkboxColors = if (selected) colors.checkbox.selected else colors.checkbox.unselected
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

            labels.table.wallet -> NameCell(
                modifier = Modifier.height(cellHeight)
                    .weight(weight.getValue(cell.column))
                    .background(if (selected) colors.hovered else Color.Transparent)
                    .separator(isLast = cell.row == table.rows.last(), color = colors.separator)
                    .pointerHoverIcon(PointerIcon.Hand)
                    .padding(horizontal = 12.dp),
                name = cell.row.item.name,
                colors = colors
            )

            labels.table.transactions -> {
                Avatar(
                    color = colors.background,
                    images = cell.row.item.transactions,
                    size = 28.dp,
                    border = 3.dp,
                    maxVisible = 4,
                    overlapPercent = 0.4f,
                    overflow = AvatarOverflow(
                        size = 28.dp,
                        fontSize = 6.sp,
                        shape = CircleShape,
                        color = ColorPair(
                            background = colors.foreground.copy(0.1f),
                            foreground = colors.foreground
                        )
                    ),
                    modifier = Modifier.height(cellHeight)
                        .weight(weight.getValue(cell.column))
                        .background(if (selected) colors.hovered else Color.Transparent)
                        .separator(isLast = cell.row == table.rows.last(), color = colors.separator)
                        .pointerHoverIcon(PointerIcon.Hand)
                        .padding(horizontal = 12.dp),
                )
            }

            labels.table.accounts -> Avatar(
                modifier = Modifier.height(cellHeight)
                    .weight(weight.getValue(cell.column))
                    .background(if (selected) colors.hovered else Color.Transparent)
                    .separator(isLast = cell.row == table.rows.last(), color = colors.separator)
                    .pointerHoverIcon(PointerIcon.Hand)
                    .padding(horizontal = 12.dp),
                color = colors.background,
                images = cell.row.item.accounts,
                size = 32.dp,
                shape = RoundedCornerShape(5.dp)
            )

            labels.table.created -> CreatedCell(
                modifier = Modifier.height(cellHeight)
                    .weight(weight.getValue(cell.column))
                    .background(if (selected) colors.hovered else Color.Transparent)
                    .separator(isLast = cell.row == table.rows.last(), color = colors.separator)
                    .pointerHoverIcon(PointerIcon.Hand)
                    .padding(horizontal = 12.dp),
                title = cell.row.item.createdBy,
                datetime = cell.row.item.createdAt,
                colors = colors,
                image = Res.drawable.user_avatar
            )

            labels.table.recent -> RecentCell(
                modifier = Modifier.height(cellHeight)
                    .weight(weight.getValue(cell.column))
                    .background(if (selected) colors.hovered else Color.Transparent)
                    .separator(isLast = cell.row == table.rows.last(), color = colors.separator)
                    .pointerHoverIcon(PointerIcon.Hand)
                    .padding(horizontal = 12.dp),
                detail = cell.row.item.recent,
                colors = colors
            )

            labels.table.amount -> Box(
                modifier = Modifier.height(cellHeight)
                    .weight(weight.getValue(cell.column))
                    .background(if (selected) colors.hovered else Color.Transparent)
                    .separator(isLast = cell.row == table.rows.last(), color = colors.separator)
                    .pointerHoverIcon(PointerIcon.Hand)
                    .padding(horizontal = 12.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Text(
                    text = "${cell.row.item.amount}/=",
                    color = colors.foreground,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 14.sp,
                    lineHeight = 1.sp
                )
            }

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
                    actions = WalletMenuAction.getMenus(labels.menu),
                    onAction = { /* TODO */ }
                )
            }
        }
    }
}
