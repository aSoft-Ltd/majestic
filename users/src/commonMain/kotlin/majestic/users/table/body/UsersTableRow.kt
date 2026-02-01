package majestic.users.table.body


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import majestic.Cell
import majestic.Checkbox
import majestic.CheckboxColors
import majestic.shared.menu.MenuOptionColors
import majestic.shared.users.label.table.InnerTableBodyLabels
import majestic.shared.users.label.table.StatusLabels
import majestic.tooling.onClick
import majestic.users.table.header.NameCell
import majestic.users.table.header.NameCellColors
import majestic.shared.users.tools.UsersData
import majestic.users.tools.data.separator
import symphony.Table
import symphony.columns.Column

data class UsersTableRowColors(
    val checkBox: CheckboxColors,
    val name: NameCellColors,
    val menuOption: MenuOptionColors
)

@Composable
internal fun RowScope.UsersTableRow(
    cell: Cell<UsersData>,
    cellHeight: Dp,
    weight: Map<Column<UsersData>, Float>,
    selected: Boolean,
    hovered: Color,
    interactionSource: MutableInteractionSource,
    table: Table<UsersData>,
    separator: Color,
    colors: UsersTableRowColors,
    labels: InnerTableBodyLabels,
    onItemClick: () -> Unit,
    menuAction: @Composable () -> Unit,
    isHighlighted: Boolean
) = when (cell.column.key) {
    "checkbox" -> Box(
        modifier = Modifier.height(cellHeight)
            .weight(weight.getValue(cell.column))
            .hoverable(interactionSource)
            .pointerHoverIcon(PointerIcon.Hand)
            .background(if (selected || isHighlighted) hovered else Color.Transparent)
            .separator(cell.row.index == table.rows.lastIndex, separator)
            .padding(horizontal = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        val checkboxColors =
            if (selected) colors.checkBox.selected else colors.checkBox.unselected
        Checkbox(
            selected = selected,
            colors = checkboxColors,
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier.size(16.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(checkboxColors.background)
                .border(1.dp, color = checkboxColors.border, RoundedCornerShape(5.dp))
                .onClick {
                    if (selected) table.selector.unSelectRowInCurrentPage(row = cell.row.number)
                    else table.selector.addSelection(row = cell.row.number)
                }
        )
    }

    labels.columns.name -> NameCell(
        modifier = Modifier.height(cellHeight)
            .weight(weight.getValue(cell.column))
            .background(if (selected || isHighlighted) hovered else Color.Transparent)
            .separator(cell.row.index == table.rows.lastIndex, separator)
            .hoverable(interactionSource)
            .pointerHoverIcon(PointerIcon.Hand)
            .onClick(onItemClick)
            .padding(horizontal = 12.dp),
        colors = colors.name,
        resource = cell.row.item.userAvatar,
        fullName = cell.row.item.fullName,
    )

    labels.columns.email, labels.columns.id, labels.columns.dateJoined, labels.columns.lastActive, labels.columns.roles, labels.columns.permission -> Box(
        modifier = Modifier.height(cellHeight)
            .weight(weight.getValue(cell.column))
            .background(if (selected || isHighlighted) hovered else Color.Transparent)
            .separator(cell.row.index == table.rows.lastIndex, separator)
            .hoverable(interactionSource)
            .pointerHoverIcon(PointerIcon.Hand)
            .onClick(onItemClick)
            .padding(horizontal = 12.dp),
        contentAlignment = when (cell.column.key) {
            labels.columns.permission, labels.columns.roles -> Alignment.Center
            else -> Alignment.CenterStart
        }
    ) {
        Text(
            modifier = Modifier.onClick(onItemClick),
            text = getLabels(cell, labels.columns).toString(),
            color = colors.name.surfaceContra,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            softWrap = false,
        )
    }

    labels.columns.status -> Box(
        modifier = Modifier.height(cellHeight)
            .weight(weight.getValue(cell.column))
            .background(if (selected || isHighlighted) hovered else Color.Transparent)
            .separator(cell.row.index == table.rows.lastIndex, separator)
            .hoverable(interactionSource)
            .pointerHoverIcon(PointerIcon.Hand)
            .onClick(onItemClick)
            .padding(horizontal = 12.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            modifier = Modifier.onClick(onItemClick),
            text = cell.row.item.status.getLabels(
                StatusLabels(
                    labels.status.invited,
                    labels.status.active,
                    labels.status.declined,
                    labels.status.revoked
                )
            ),
            color = cell.row.item.status.getColors(),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            softWrap = false,
        )
    }

    else -> Box(
        modifier = Modifier.height(cellHeight)
            .weight(weight.getValue(cell.column))
            .background(if (selected || isHighlighted) hovered else Color.Transparent)
            .hoverable(interactionSource)
            .pointerHoverIcon(PointerIcon.Hand)
            .separator(cell.row.index == table.rows.lastIndex, separator)
            .padding(horizontal = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        menuAction()
    }
}