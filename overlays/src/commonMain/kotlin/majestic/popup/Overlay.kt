package majestic.popup

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

class Overlay(
    val modifier: Modifier = Modifier,
    val shape: Shape = RoundedCornerShape(8.0.dp),
    val content: @Composable ColumnScope.() -> Unit
)