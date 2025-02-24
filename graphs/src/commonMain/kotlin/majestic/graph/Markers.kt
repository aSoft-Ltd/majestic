package majestic.graph

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Markers(
    val color: Color = Color.White,
    val radius: Dp = 5.dp,
    val style: DrawStyle = Fill
)