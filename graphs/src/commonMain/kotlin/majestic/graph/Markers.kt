package majestic.graph

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Markers(
    val color: Color = Color.White,
    val length: Dp = 15.dp,
    val width: Dp = 1.dp,
    val angle: Float = 0f,
    val label: @Composable (value: Float) -> Unit = { Text("$it") }
)