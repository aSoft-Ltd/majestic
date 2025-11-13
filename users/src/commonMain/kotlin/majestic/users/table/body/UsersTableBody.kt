package majestic.users.table.body

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.Cell
import majestic.ThemeColor
import majestic.tooling.onClick
import majestic.users.table.ListItem
import majestic.users.table.ListLabels
import majestic.users.table.header.tools.TableStatus
import majestic.users.table.header.tools.getStatusLabels
import majestic.users.tools.ColumnLabels
import majestic.users.tools.data.UsersData
import majestic.users.tools.data.separator
import symphony.Table
import symphony.columns.Column

data class UserTableBodyColors(
    val separator: Color,
    val hovered: Color,
    val background: Color,
    val theme: ThemeColor
)

data class UserTableLabels(
    val columns: ColumnLabels,
    val status: TableStatus
)

data class UsersTableBodyProperties(
    val labels: UserTableLabels,
    val colors: UserTableBodyColors
)

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
        theme = props.colors.theme,
        labels = props.labels,
        onItemClick = onItemClick,
        menuAction = menuAction
    )

    is Portrait -> {
        ListItem(
            user = cell.row.item,
            theme = props.colors.theme,
            modifier = Modifier
                .fillMaxWidth()
                .onClick(callback = onItemClick)
                .separator(cell.row.index == table.rows.lastIndex, props.colors.theme.surface.contra.color.copy(0.05f))
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