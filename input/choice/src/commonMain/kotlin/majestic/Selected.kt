package majestic

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

class Selected<T>(
    val modifier: Modifier = Modifier,
    val alignment: Alignment = Alignment.TopStart,
    val content: @Composable BoxScope.(SelectedItem<T>) -> Unit
) {
    class SelectedItem<out T>(
        val expanded: Boolean,
        val selected: T?
    )
}