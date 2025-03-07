package majestic

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import kollections.first
import kollections.isNotEmpty
import kollections.last
import kotlinx.coroutines.delay
import majestic.graph.LinePlot
import majestic.graph.Mark
import majestic.graph.tools.YProjection
import majestic.graph.tools.drawXAxis
import majestic.graph.tools.drawYAxis
import majestic.graph.tools.plot.plot
import majestic.graph.tools.points

@Composable
fun Graph(
    modifier: Modifier = Modifier,
    onClick: ((Mark) -> Unit)? = null,
    builder: GraphBuilderScope.() -> Unit
) {
    var size by remember { mutableStateOf(IntSize.Zero) }
    var xSize by remember { mutableStateOf(IntSize.Zero) }
    val density = LocalDensity.current
    val graph = GraphBuilderScope().apply(builder)
    var mark by remember { mutableStateOf<Mark?>(null) }
    LaunchedEffect(mark?.timeout) {
        val timeout = mark?.timeout ?: return@LaunchedEffect
        delay(timeout)
        mark = null
    }
    Row(modifier = modifier) {
        val yAxis = graph.y
        val yLabels = yAxis?.label
        val yPoints = yAxis?.points(0f, size.height.toFloat(), density, inverted = true)?.reversed()
        if (yLabels != null && yPoints != null) Box(
            modifier = Modifier.fillMaxHeight().padding(end = 8.dp, bottom = with(density) { xSize.height.toDp() }),
            contentAlignment = Alignment.TopEnd,
        ) {
            for (point in yPoints) {
                var labelSize by remember { mutableStateOf(IntSize.Zero) }
                val y = with(density) { (point.dst - (labelSize.height / 2)).toDp() }
                Box(
                    modifier = Modifier.offset(y = y).onPlaced { labelSize = it.size },
                    contentAlignment = Alignment.Center
                ) {
                    yAxis.label(point.src)
                }
            }
        }

        val xAxis = graph.x
        val xLabels = xAxis?.label
        val xPoints = xAxis?.points(0f, size.width.toFloat(), density, inverted = false)
        Column(modifier = Modifier.weight(1f).fillMaxSize()) {
            Box(
                Modifier.weight(1f)
                    .fillMaxSize()
            ) {
                Canvas(
                    modifier = Modifier.fillMaxSize()
                        .onPlaced { size = it.size }
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onTap = { offset ->
                                    mark = graph.find(offset, 20f)?.also { onClick?.invoke(it) }
                                }
                            )
                        }
                ) {
                    if (yAxis != null && yPoints != null) drawYAxis(yAxis, yPoints)
                    if (xAxis != null && xPoints != null) drawXAxis(xAxis, xPoints)
                    if (xPoints?.isNotEmpty() == true && yPoints?.isNotEmpty() == true) {
                        val top = yPoints.first()
                        plot(graph, xPoints.last(), YProjection(yAxis, size.height - top.dst, offset = top.dst))
                    }
                }
                val m = mark
                if (m != null) when (val plot = m.plot) {
                    is LinePlot -> Box(
                        modifier = Modifier.offset(
                            x = with(density) { m.offset.x.toDp() },
                            y = with(density) { m.offset.y.toDp() }
                        ),
                        contentAlignment = Alignment.Center
                    ) {
                        plot.markers?.content?.invoke(m)
                    }
                }
            }
            if (xLabels != null && xPoints != null) Box(
                modifier = Modifier.fillMaxWidth()
                    .padding(top = if (xAxis.ticks == null) 0.dp else 8.dp)
                    .onPlaced { xSize = it.size },
            ) {
                val angle = xAxis.ticks?.angle?.inDegrees() ?: 0f
                for (point in xPoints) {
                    var labelSize by remember { mutableStateOf(IntSize.Zero) }
                    val x = with(density) { (point.dst - (labelSize.width / 2)).toDp() }
                    Box(
                        modifier = Modifier.offset(x = x)
                            .onPlaced { labelSize = it.size }
                            .rotate(angle)
                    ) {
                        xAxis.label(point.src)
                    }
                }
            }
        }
    }
}