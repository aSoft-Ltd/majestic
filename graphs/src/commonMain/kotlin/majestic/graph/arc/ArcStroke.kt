package majestic.graph.arc

import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class ArcStroke(
    val width: Dp = 8.dp,
    val cap: StrokeCap = StrokeCap.Companion.Round
)