package majestic.popup

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

class Item<T>(
    val modifier: Modifier = Modifier,
    val tag: (T) -> String = { "$it" },
    val onClick: (T) -> Unit = {},
    val content: @Composable (T) -> Unit,
)