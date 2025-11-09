package majestic.users.table

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cinematic.watchAsState
import composex.screen.orientation.Landscape
import composex.screen.orientation.ScreenOrientation
import majestic.Cell
import majestic.LazyTable
import majestic.users.tools.UsersData
import majestic.users.tools.data.getSelectedRows
import symphony.Table
import symphony.columns.Column

@Composable
fun UsersList(
    modifier: Modifier = Modifier,
    table: Table<UsersData>,
    orientation: ScreenOrientation,
    header: @Composable RowScope.(column: Column<UsersData>, selectAll: Boolean, selectCount: Int, cellHeight: Dp) -> Unit,
    body: @Composable RowScope.(cell: Cell<UsersData>, selected: Boolean, cellHeight: Dp, table: Table<UsersData>) -> Unit,
) {
    val selectCount = getSelectedRows(table)
    val cellHeight = 70.dp

    Box(
        modifier = modifier,
    ) {
        table.selector.selected.watchAsState()
        LazyTable(
            modifier = Modifier
                .clip(RoundedCornerShape(if (orientation is Landscape) 20.dp else 0.dp))
                .background(Color.Transparent, RoundedCornerShape(if (orientation is Landscape) 20.dp else 0.dp))
                .fillMaxWidth().horizontalScroll(rememberScrollState()),
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
}