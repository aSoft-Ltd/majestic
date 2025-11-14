package majestic.users.table

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cinematic.watchAsState
import majestic.Cell
import majestic.LazyTable
import majestic.users.tools.data.UsersData
import majestic.users.tools.data.getSelectedRows
import symphony.Table
import symphony.columns.Column

@Composable
fun UsersList(
    modifier: Modifier = Modifier,
    table: Table<UsersData>,
    header: @Composable RowScope.(column: Column<UsersData>, selectAll: Boolean, selectCount: Int, cellHeight: Dp) -> Unit,
    body: @Composable RowScope.(cell: Cell<UsersData>, selected: Boolean, cellHeight: Dp, table: Table<UsersData>) -> Unit,
) {
    val selectCount = getSelectedRows(table)
    val cellHeight = 70.dp

    table.selector.selected.watchAsState()
    LazyTable(
        modifier = modifier,
        table = table,
        columns = { column ->
            val selectedAll = table.selector.isCurrentPageSelectedWholly()
            header(column, selectedAll, selectCount, cellHeight)
        }
    ) { cell ->
        val selected = table.selector.isRowSelectedOnCurrentPage(cell.row.number)
        body(cell, selected, cellHeight, table)
    }
}