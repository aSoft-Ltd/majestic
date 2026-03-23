package majestic.users.dashboard.table

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cinematic.watchAsState
import majestic.LazyTable
import majestic.editor.toolbar.underline
import majestic.shared.users.UsersLabels
import majestic.shared.users.tools.UsersData
import majestic.users.dashboard.roles.Header
import majestic.users.dashboard.roles.HeaderProps
import majestic.users.dashboard.roles.Labels
import majestic.users.dashboard.table.body.UsersTableRow
import majestic.users.dashboard.table.header.UserTableHeaderProps
import majestic.users.dashboard.table.header.UsersTableHeader
import majestic.users.table.body.UsersTableBodyProperties
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
    props: UserTableProps,
    onItemClick: () -> Unit,
    weight: Map<Column<UsersData>, Float>,
    add: () -> Unit,
    manage: () -> Unit,
    menuAction: @Composable () -> Unit
) {
    val cellHeight = 70.dp
    table.selector.selected.watchAsState()
    val interactionSources = remember { mutableMapOf<Int, MutableInteractionSource>() }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header(
            modifier = Modifier
                .fillMaxWidth()
                .background(props.header.colors.background)
                .underline(props.header.colors.separator)
                .wrapContentHeight()
                .padding(horizontal = 20.dp, vertical = 5.dp)
                .height(60.dp),
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
            modifier = Modifier
                .weight(1f)
                .background(props.body.colors.table.body),
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
            UsersTableRow(
                    cell = cell,
                    cellHeight = cellHeight,
                    weight = weight,
                    selected = selected,
                    table = table,
                    separator = props.body.colors.table.bodyBorder,
                    colors = props.body.colors,
                    labels = props.body.labels,
                    onItemClick = onItemClick,
                    interactionSources = interactionSources,
                    menuAction = menuAction
            )
        }
    }
}