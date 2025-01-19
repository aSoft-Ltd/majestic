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
import kollections.mapIndexed
import symphony.Row
import kotlin.jvm.JvmName
import symphony.Table as TableManager

@Composable
fun <D> LazyTable(
    table: TableManager<D>,
    modifier: Modifier = Modifier,
    cell: @Composable RowScope.(Cell<D>) -> Unit = { GenericCell(it) }
) {
    val columns = table.columns.current.watchAsState()
    LazyTable(
        rows = table.rows,
        columns = Columns(columns) { column ->
            Text(column.name)
        },
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