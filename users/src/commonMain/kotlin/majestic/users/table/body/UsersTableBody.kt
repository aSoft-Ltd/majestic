package majestic.users.table.body

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.Cell
import majestic.shared.users.label.table.InnerTableBodyLabels
import majestic.shared.users.label.table.StatusLabels
import majestic.tooling.onClick
import majestic.users.table.ListItem
import majestic.users.table.ListItemColors
import majestic.users.table.ListLabels
import majestic.users.table.LocalUsersRowInteractionSource
import majestic.shared.users.tools.UsersData
import majestic.users.tools.data.separator
import symphony.Table
import symphony.columns.Column



data class UsersTableBodyProperties(
    val labels: InnerTableBodyLabels,
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
) {
    val interaction = LocalUsersRowInteractionSource
        .current ?: remember { MutableInteractionSource() }
    val isHighlighted by interaction.collectIsHoveredAsState()

    when (orientation) {
        is Landscape -> UsersTableRow(
            cell = cell,
            cellHeight = cellHeight,
            weight = weight,
            isHighlighted = isHighlighted,
            selected = selected,
            hovered = props.colors.hovered,
            table = table,
            separator = props.colors.separator,
            colors = props.colors.row,
            labels = props.labels,
            onItemClick = onItemClick,
            menuAction = menuAction,
            interactionSource = interaction
        )

        is Portrait -> ListItem(
            user = cell.row.item,
            colors = props.colors.listItem,
            modifier = Modifier
                .fillMaxWidth()
                .onClick(callback = onItemClick)
                .separator(
                    cell.row.index == table.rows.lastIndex,
                    props.colors.listItem.surfaceContra.copy(0.05f)
                )
                .padding(10.dp),
            menuAction = menuAction,
            labels = ListLabels(
                role = props.labels.columns.roles,
                permission = props.labels.columns.permission,
                status = StatusLabels(
                    props.labels.status.invited,
                    props.labels.status.active,
                    props.labels.status.declined,
                    props.labels.status.revoked
                )
            )
        )

    }
}
