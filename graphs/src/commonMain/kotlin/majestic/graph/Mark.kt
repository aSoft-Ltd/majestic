package majestic.graph

import androidx.compose.ui.geometry.Offset
import kotlin.time.Duration

data class Mark(
    val plot: Plot,
    val timeout: Duration,
    val point: Point,
    val offset: Offset
)