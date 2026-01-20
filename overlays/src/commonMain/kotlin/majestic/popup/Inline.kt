package majestic.popup

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

class Inline(
    val modifier: Modifier = Modifier,
    val tag: String? = null,
    val alignment: Alignment = Alignment.TopStart,
    val content: @Composable BoxScope.(expanded: Boolean) -> Unit
)