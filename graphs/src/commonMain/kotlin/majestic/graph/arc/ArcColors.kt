package majestic.graph.arc

import androidx.compose.ui.graphics.Color

data class ArcColors(
    val track: Color = Color.Companion.White.copy(alpha = .10f),
    val progress: Color = Color.Companion.White
)