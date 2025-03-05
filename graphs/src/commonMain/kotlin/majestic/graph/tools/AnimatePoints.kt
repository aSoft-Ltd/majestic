package majestic.graph.tools

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import kollections.isNotEmpty
import kotlinx.coroutines.delay
import majestic.graph.Point
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

@Composable
fun animatePoints(
    points: List<Point> = emptyList(),
    duration: Duration = 1.seconds,
    delay: Duration = 0.seconds,
): SnapshotStateList<Point> {
    val data = remember(points) { mutableStateListOf<Point>() }
    LaunchedEffect(duration, delay, points) {
        delay(delay)
        val dt = if (points.isNotEmpty()) (duration / points.size) else 0.seconds
        for (p in points) {
            data.add(p)
            delay(dt)
        }
    }
    return data
}