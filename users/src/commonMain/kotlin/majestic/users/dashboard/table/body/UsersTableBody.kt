package majestic.users.dashboard.table.body

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.Cell
import majestic.tooling.onClick
import majestic.users.table.ListItem
import majestic.users.table.ListLabels
import majestic.users.table.body.UsersTableBodyProperties
import majestic.users.table.header.tools.getStatusLabels
import majestic.users.tools.data.UsersData
import majestic.users.tools.data.separator
import symphony.Table
import symphony.columns.Column


@Composable
fun RowScope.UsersTableBody(
    orientation: ScreenOrientation,
    cell: Cell<UsersData>,
    cellHeight: Dp,
    selected: Boolean,
    table: Table<UsersData>,
    props: UsersTableBodyProperties,
    onItemClick: () -> Unit,
    weight: Map<Column<UsersData>, Float>,
    menuAction: @Composable () -> Unit
) = when (orientation) {
    is Landscape -> UsersTableRow(
        cell = cell,
        cellHeight = cellHeight,
        weight = weight,
        selected = selected,
        hovered = props.colors.hovered,
        table = table,
        separator = props.colors.separator,
        colors = props.colors.row,
        labels = props.labels,
        onItemClick = onItemClick,
        menuAction = menuAction
    )

    is Portrait -> {
        ListItem(
            user = cell.row.item,
            colors = props.colors.listItem,
            modifier = Modifier
                .fillMaxWidth()
                .onClick(callback = onItemClick)
                .separator(
                    isLast = cell.row.index == table.rows.lastIndex,
                    color = props.colors.listItem.surfaceContra.copy(0.05f)
                )
                .padding(10.dp),
            menuAction = menuAction,
            labels = ListLabels(
                role = props.labels.columns.roles,
                permission = props.labels.columns.permission,
                status = getStatusLabels(props.labels.status)
            )
        )

    }
}