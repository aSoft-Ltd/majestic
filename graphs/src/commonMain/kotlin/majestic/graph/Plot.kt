package majestic.graph

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke

internal sealed interface Plot

data class LinePlot(
    val color: Color,
    val stroke: Stroke,
    val type: Type,
    val points: Points
) : Plot {
    enum class Type {
        Straight, Curve
    }
}