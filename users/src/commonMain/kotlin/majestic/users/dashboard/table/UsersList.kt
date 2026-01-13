package majestic.users.dashboard.table

import androidx.compose.foundation.background
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
import majestic.editor.toolbar.underline
import majestic.users.dashboard.roles.Header
import majestic.users.dashboard.roles.HeaderProps
import majestic.users.dashboard.roles.Labels
import majestic.users.dashboard.table.body.UsersTableBody
import majestic.users.dashboard.table.header.tools.UserTableHeaderProps
import majestic.users.dashboard.table.header.tools.UsersTableHeader
import majestic.users.labels.UsersLabels
import majestic.users.table.body.UsersTableBodyProperties
import majestic.users.table.tools.data.getOptions
import majestic.users.tools.data.UsersData
import majestic.users.tools.data.getSelectedRows
import majestic.users.tools.menu.MenuOption
import symphony.Table
import symphony.columns.Column

data class UserTableProps(
    val header: HeaderProps,
    val head: UserTableHeaderProps,
    val body: UsersTableBodyProperties
)

@Composable
fun UsersTable(
    modifier: Modifier = Modifier,
    table: Table<UsersData>,
    labels: UsersLabels,
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
                .background(props.header.colors.background)
                .underline(color = props.header.colors.separator ?: props.header.colors.background)
                .wrapContentHeight()
                .padding(horizontal = 20.dp, vertical = 5.dp)
                .height(80.dp),
            props = HeaderProps(
                labels = Labels(
                    title = labels.dashboard.table.title,
                    manage = labels.dashboard.table.manage
                ),
                colors = props.header.colors
            ),
            add = add,
            manage = manage
        )
        LazyTable(
            modifier = Modifier.weight(1f),
            table = table,
            columns = { column ->
                UsersTableHeader(
                    column = column,
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
                menuAction = {
                    MenuOption(
                        orientation = orientation,
                        actions = getOptions(labels.table),
                        colors = props.body.colors.row.menuOption,
                    ) { action -> }
                }
            )
        }
    }
}