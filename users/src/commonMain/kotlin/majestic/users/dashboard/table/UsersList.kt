package majestic.users.dashboard.table

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cinematic.watchAsState
import composex.screen.orientation.ScreenOrientation
import majestic.LazyTable
import majestic.users.dashboard.roles.Header
import majestic.users.dashboard.roles.HeaderProps
import majestic.users.dashboard.table.body.UsersTableBody
import majestic.users.dashboard.table.header.tools.UsersTableHeader
import majestic.users.table.body.UsersTableBodyProperties
import majestic.users.table.header.tools.HeaderProperties
import majestic.users.tools.data.UsersData
import majestic.users.tools.data.getSelectedRows
import symphony.Table
import symphony.columns.Column

data class UserTableProps(
    val header: HeaderProps,
    val head: HeaderProperties,
    val body: UsersTableBodyProperties
)

@Composable
fun UsersTable(
    modifier: Modifier = Modifier,
    table: Table<UsersData>,
    orientation: ScreenOrientation,
    props: UserTableProps,
    onItemClick: () -> Unit,
    weight: Map<Column<UsersData>, Float>,
    add: () -> Unit,
    manage: () -> Unit
) {
    val selectCount = getSelectedRows(table)
    val selectedAll = table.selector.isCurrentPageSelectedWholly()
    val cellHeight = 70.dp

    table.selector.selected.watchAsState()
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 16.dp, vertical = 10.dp)
                .height(80.dp),
            props = props.header,
            add = add,
            manage = manage
        )
        LazyTable(
            modifier = Modifier.weight(1f),
            table = table,
            columns = { column ->
                UsersTableHeader(
                    column = column,
                    count = selectCount,
                    weight = weight,
                    props = props.head,
                    table = table
                )
            }
        ) { cell ->
            val selected = table.selector.isRowSelectedOnCurrentPage(cell.row.number)
            UsersTableBody(
                orientation = orientation,
                cell = cell,
                cellHeight = cellHeight,
                selected = selected,
                table = table,
                props = props.body,
                onItemClick = onItemClick,
                weight = weight,
                menuAction = {}
            )
        }
    }
}