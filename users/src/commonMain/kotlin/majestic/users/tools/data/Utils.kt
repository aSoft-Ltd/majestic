package majestic.users.tools.data

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import cinematic.watchAsState
import kollections.Set
import majestic.users.tools.UsersData
import symphony.Table
import symphony.columns.Column
import symphony.selected.LinearSelected
import symphony.selected.LinearSelectedGlobal
import symphony.selected.LinearSelectedItem
import symphony.selected.LinearSelectedItems
import symphony.selected.LinearSelectedNone

data class HeaderLabels(
    val checkbox: String,
    val name: String,
    val email: String,
    val id: String,
    val dateJoined: String,
    val lastActive: String,
    val roles: String,
    val permission: String,
    val status: String,
)

@Composable
fun getWeights(
    columns: Set<Column<UsersData>>,
    labels: HeaderLabels
): Map<Column<UsersData>, Float> = remember(columns) {
    buildMap {
        for (column in columns) {
            this[column] = when (column.key) {
                labels.checkbox -> 2f
                labels.name -> 4.5f
                labels.email -> 6f
                labels.id -> 8f
                labels.dateJoined -> 4f
                labels.lastActive -> 4f
                labels.roles -> 4f
                labels.permission -> 4f
                labels.status -> 4f
                else -> 2f
            }
        }
    }
}

@Composable
fun getSelectedRows(table: Table<UsersData>): Int =
    when (val selection = table.selector.selected.watchAsState() as LinearSelected) {
        is LinearSelectedGlobal<*> -> table.rows.size
        is LinearSelectedItem<*> -> 1
        is LinearSelectedItems<*> -> selection.page.flatMap { it.value }.count()
        is LinearSelectedNone -> 0
    }

//internal fun getEnrolledData(
//    cell: Cell<EnrolledData>,
//    labels: BulkLabels,
//    table: Table<EnrolledData>,
//    selected: Boolean,
//    theme: ThemeColor,
//    hovered: Color
//) = Data(
//    item = DataItem(
//        title = cell.row.item.title,
//        campus = cell.row.item.campus,
//        count = cell.row.item.enrolledCount,
//        status = cell.row.item.status.getLabel(labels.table.enrolled.body.status),
//        actions = listOf(
//            OptionMenu(
//                BulkMenuAction.Recall.getLabel(labels.table.enrolled.body.bulkActions),
//                BulkMenuAction.Recall
//            ),
//            OptionMenu(
//                BulkMenuAction.Select.getLabel(labels.table.enrolled.body.bulkActions),
//                BulkMenuAction.Select
//            )
//        ),
//        isLast = cell.row.index == table.rows.lastIndex,
//        selected = selected
//    ),
//    colors = Colors(
//        title = theme.surface.actual.color,
//        campus = theme.surface.contra.color,
//        status = cell.row.item.status.getColor(theme),
//        hovered = hovered
//    )
//)
//
//