package majestic

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
    columns: @Composable RowScope.(Column<D>) -> Unit = { Text(it.name, modifier = Modifier.weight(1f)) },
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
) = LazyColumn(modifier) {
    stickyHeader(columns) {
        Row(modifier = columns.modifier.fillMaxWidth()) {
            for (column in columns.data) columns.renderer(this, column)
        }
    }
    items(rows) { row ->
        Row(modifier = Modifier.fillMaxWidth()) {
            for (column in columns.data) cell(this, Cell(column, row))
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