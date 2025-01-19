package majestic

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import symphony.columns.Column

class Columns<D>(
    internal val data: Collection<Column<D>>,
    internal val modifier: Modifier = Modifier,
    internal val renderer: @Composable RowScope.(Column<D>) -> Unit
)