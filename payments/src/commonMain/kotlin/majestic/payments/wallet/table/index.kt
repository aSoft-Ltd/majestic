package majestic.payments.wallet.table

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cinematic.watchAsState
import kollections.iterator
import majestic.Checkbox
import majestic.CheckboxColors
import majestic.ColorPair
import majestic.LazyTable
import majestic.payments.labels.WalletLabels
import majestic.payments.tools.separator
import majestic.tooling.onClick
import symphony.Table

data class TableColors(
    val background: Color,
    val foreground: Color,
    val separator: Color,
    val hovered: Color,
    val header: ColorPair,
    val checkbox: CheckboxColors
)

@Composable
fun WalletTable(
    labels: WalletLabels,
    colors: TableColors,
    table: Table<PaymentWallet>,
    modifier: Modifier = Modifier
) {
    val columns = table.columns.current.watchAsState()
    val weight = remember(columns) {
        buildMap {
            for (column in columns) {
                this[column] = when (column.key) {
                    labels.table.checkbox -> 2f
                    labels.table.transactions -> 7f
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
                    .background(header.background)
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
                    .background(header.background)
                    .separator(color = colors.separator)
                    .padding(vertical = 20.dp, horizontal = 12.dp),
                color = header.foreground.copy(0.6f)
            )
        }
    ) { cell ->
        val cellHeight = 70.dp
        val selected = table.selector.isRowSelectedOnCurrentPage(cell.row.number)
    }
}
