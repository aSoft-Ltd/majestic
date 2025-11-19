package majestic.users.table

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
    selectionBar: @Composable ColumnScope.(users: Int) -> Unit = {},
    header: @Composable RowScope.(column: Column<UsersData>, selectCount: Int) -> Unit,
    body: @Composable RowScope.(cell: Cell<UsersData>, selected: Boolean, cellHeight: Dp, table: Table<UsersData>) -> Unit,
) {
    val selectCount = getSelectedRows(table)
    val selectedAll = table.selector.isCurrentPageSelectedWholly()
    val cellHeight = 70.dp

    table.selector.selected.watchAsState()
    Column(
        modifier = Modifier.wrapContentSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        selectionBar(selectCount)
        LazyTable(
            modifier = modifier,
            table = table,
            columns = { column ->
                header(column, selectCount)
            }
        ) { cell ->
            val selected = table.selector.isRowSelectedOnCurrentPage(cell.row.number)
            body(cell, selected, cellHeight, table)
        }
    }
}