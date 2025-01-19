package majestic

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import symphony.Row

class Rows<D>(
    internal val data: List<Row<D>>,
    internal val modifier: Modifier = Modifier,
    internal val renderer: @Composable RowScope.(Row<D>) -> Unit
)