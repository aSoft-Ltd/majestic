package majestic.graph.arc

import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp

data class ArcLayout(
    val fit: ArcFit = ArcFit.TopHalf,
    val offset: DpOffset = DpOffset(0.dp, 0.dp)
)