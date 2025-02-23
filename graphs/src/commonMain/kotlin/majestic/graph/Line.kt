package majestic.graph

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke

data class Line(
    val color: Color = Color.White,
    val stroke: Stroke = Stroke(width = 1f)
) {
    companion object {
        val axis by lazy {
            Line(Color.White, stroke = Stroke(width = 2f))
        }

        val grid by lazy {
            Line(Color.White.copy(alpha = 0.2f), stroke = Stroke(width = 1f))
        }
    }
}