package majestic.users.table.body

import majestic.Cell
import majestic.users.tools.ColumnLabels
import majestic.users.tools.data.UsersData

fun getLabels(cell: Cell<UsersData>, labels: ColumnLabels) = when (cell.column.key) {
    labels.email -> cell.row.item.email
    labels.id -> cell.row.item.id
    labels.dateJoined -> cell.row.item.dateJoined.first
    labels.lastActive -> cell.row.item.lastActive.first
    labels.roles -> cell.row.item.roles
    else -> cell.row.item.permissions
}