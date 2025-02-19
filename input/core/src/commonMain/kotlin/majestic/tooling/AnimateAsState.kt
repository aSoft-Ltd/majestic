package majestic.tooling

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

@Composable
fun animateAsState(
    to: Number,
    from: Number = 0,
    steps: Int = 10,
    duration: Duration = 1.seconds
): State<Number> {
    val start = remember(from) { from.toFloat() }
    val end = remember(to) { to.toFloat() }
    val state = remember { mutableStateOf(start) }
    LaunchedEffect(from, to, duration, steps) {
        val dv = (end - start) / steps
        val dt = duration / steps
        launch {
            var value = start
            do {
                delay(dt)
                value += dv
                state.value = value
            } while (value < end)
            state.value = end
        }
    }
    return state
}