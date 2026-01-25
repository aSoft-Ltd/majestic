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
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.LazyTable
import majestic.editor.toolbar.underline
import majestic.shared.users.UsersLabels
import majestic.tooling.onClick
import majestic.users.dashboard.roles.Header
import majestic.users.dashboard.roles.HeaderProps
import majestic.users.dashboard.roles.Labels
import majestic.users.dashboard.table.body.UsersTableRow
import majestic.users.dashboard.table.header.UserTableHeaderProps
import majestic.users.dashboard.table.header.UsersTableHeader
import majestic.users.table.ListItem
import majestic.users.table.ListLabels
import majestic.users.table.body.UsersTableBodyProperties
import majestic.users.table.header.tools.getStatusLabels
import majestic.users.tools.data.UsersData
import majestic.users.tools.data.separator
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
    manage: () -> Unit,
    menuAction: @Composable () -> Unit
) {
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
            when (orientation) {
                is Landscape -> UsersTableRow(
                    cell = cell,
                    cellHeight = cellHeight,
                    weight = weight,
                    selected = selected,
                    hovered = props.body.colors.hovered,
                    table = table,
                    separator = props.body.colors.separator,
                    colors = props.body.colors.row,
                    labels = props.body.labels,
                    onItemClick = onItemClick,
                    menuAction = menuAction
                )

                is Portrait -> ListItem(
                    user = cell.row.item,
                    colors = props.body.colors.listItem,
                    modifier = Modifier
                        .fillMaxWidth()
                        .onClick(callback = onItemClick)
                        .separator(
                            isLast = cell.row.index == table.rows.lastIndex,
                            color = props.body.colors.listItem.surfaceContra.copy(0.05f)
                        )
                        .padding(10.dp),
                    menuAction = menuAction,
                    labels = ListLabels(
                        role = props.body.labels.columns.roles,
                        permission = props.body.labels.columns.permission,
                        status = getStatusLabels(props.body.labels.status)
                    )
                )

            }
        }
    }
}