package majestic

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import symphony.Row
import symphony.columns.Column

class Cell<D>(
    val column: Column<D>,
    val row: Row<D>
)

@Composable
fun <D> GenericCell(
    cell: Cell<D>,
    modifier: Modifier = Modifier
) = Column(modifier) {
    Text("${cell.column.asData?.resolve(cell.row)}")
    Text(cell.column.name, color = LocalContentColor.current.copy(alpha = 0.5f))
}