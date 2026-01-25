package majestic.layouts

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.ui.Modifier

sealed interface FlexScope {
    class Row(val scope: RowScope) : FlexScope {
        override fun Modifier.flex(value: Float, fill: Boolean): Modifier = with(scope) {
            weight(value, fill)
        }
    }

    class Column(val scope: ColumnScope) : FlexScope {
        override fun Modifier.flex(value: Float, fill: Boolean): Modifier = with(scope) {
            weight(value, fill)
        }
    }

    fun Modifier.flex(value: Float, fill: Boolean = true): Modifier
}
