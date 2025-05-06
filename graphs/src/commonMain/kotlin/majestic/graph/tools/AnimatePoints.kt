package majestic.graph.tools

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
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

@Composable
fun animateRandomLoadingPoints(
    x: IntRange = 0..100,
    y: IntRange = 0..100,
    duration: Duration = 1.seconds,
    delay: Duration = 0.seconds,
): SnapshotStateList<Point> {
    val data = remember(x, y) { mutableStateListOf<Point>() }
    LaunchedEffect(duration, delay, x, y) {
        delay(delay)
        var expanding = true
        val dt = duration / x.step / 2
        var r = x.first.toFloat()
        while (true) {
            if (expanding) {
                data.add(Point(r, y.random().toFloat()))
                r += x.step
                if (r > x.last) {
                    expanding = false
                }
            } else {
                data.removeAll { it.x == r }
                r -= x.step
                if (r <= x.first) {
                    data.clear()
                    expanding = true
                }
            }
            delay(dt)
        }
    }
    return data
}