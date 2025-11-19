package majestic.users.tools.data

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cinematic.watchAsState
import kollections.Set
import majestic.users.tools.ColumnLabels
import symphony.Table
import symphony.columns.Column
import symphony.selected.LinearSelected
import symphony.selected.LinearSelectedGlobal
import symphony.selected.LinearSelectedItem
import symphony.selected.LinearSelectedItems
import symphony.selected.LinearSelectedNone

@Composable
fun getWeights(
    columns: Set<Column<UsersData>>,
    labels: ColumnLabels
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


internal fun Modifier.separator(isLast: Boolean, color: Color) = drawBehind {
    val strokeWidth = 1.dp.toPx()
    drawLine(
        color = if (isLast) Color.Transparent else color,
        start = Offset(0f, size.height - strokeWidth / 2),
        end = Offset(size.width, size.height - strokeWidth / 2),
        strokeWidth = strokeWidth
    )
}