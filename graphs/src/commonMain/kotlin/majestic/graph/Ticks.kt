package majestic.graph

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import majestic.math.Angle
import majestic.math.deg

data class Ticks(
    val color: Color = Color.White,
    val length: Dp = 4.dp,
    val angle: Angle = 0.deg,
    val stroke: Stroke = Stroke(width = 2f)
) {
    companion object {
        val default by lazy {
            Ticks(
                color = Color.White,
                length = 3.dp,
                angle = 0.deg,
                stroke = Stroke()
            )
        }
    }
}