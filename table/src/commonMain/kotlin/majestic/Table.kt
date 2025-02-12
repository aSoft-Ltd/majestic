package majestic

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import cinematic.watchAsState
import kollections.filter
import kollections.mapIndexed
import symphony.LinearTable
import symphony.Row
import symphony.Table
import symphony.VisibleVisibility
import symphony.columns.Column
import kotlin.jvm.JvmName

@Composable
fun <D> LazyTable(
    table: Table<D>,
    modifier: Modifier = Modifier,
    columns: (@Composable RowScope.(Column<D>) -> Unit)? = { Text(it.name, modifier = Modifier.weight(1f)) },
    cell: @Composable RowScope.(Cell<D>) -> Unit = { GenericCell(it) }
) {
    table as LinearTable
    val cols = table.columns.current.watchAsState().filter { it.visibility == VisibleVisibility }
    val rows = table.paginator.current.watchAsState().data?.items ?: kollections.emptyList()
    LazyTable(
        rows = rows,
        columns = Columns(cols, renderer = columns),
        modifier = modifier,
        cell = cell
    )
}

@OptIn(ExperimentalFoundationApi::class)
@JvmName("LazyTableRow")
@Composable
fun <D> LazyTable(
    rows: List<Row<D>>,
    columns: Columns<D>,
    modifier: Modifier = Modifier,
    cell: @Composable RowScope.(Cell<D>) -> Unit = { GenericCell(it) }
) {
    var width by remember { mutableStateOf(300.dp) }

    val density = LocalDensity.current

    LazyColumn(modifier.onPlaced { width = with(density) { (it.parentCoordinates?.size?.width ?: 300).toDp() } }) {
        if (columns.renderer != null) stickyHeader(columns) {
            Row(modifier = columns.modifier.width(width)) {
                for (column in columns.data) columns.renderer.invoke(this, column)
            }
        }
        items(rows) { row ->
            Row(modifier = Modifier.width(width)) {
                for (column in columns.data) cell(this, Cell(column, row))
            }
        }
    }
}

@Composable
fun <D> LazyTable(
    data: List<D>,
    columns: Columns<D>,
    modifier: Modifier = Modifier,
    cell: @Composable RowScope.(Cell<D>) -> Unit = { GenericCell(it) }
) = LazyTable(
    rows = data.mapIndexed { index, item -> Row(index, item) },
    columns = columns,
    modifier = modifier,
    cell = cell
)
