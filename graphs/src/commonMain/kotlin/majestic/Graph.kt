package majestic

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import majestic.graph.tools.drawXAxis
import majestic.graph.tools.drawYAxis
import majestic.graph.tools.plot.plot
import majestic.graph.tools.points

@Composable
fun Graph(
    modifier: Modifier = Modifier,
    builder: GraphBuilderScope.() -> Unit
) {
    var size by remember { mutableStateOf(IntSize.Zero) }
    var xSize by remember { mutableStateOf(IntSize.Zero) }
    val density = LocalDensity.current
    val graph = GraphBuilderScope().apply(builder)
    Row(modifier = modifier) {
        val yAxis = graph.y
        val yLabels = yAxis?.markers?.label
        val yPoints = yAxis?.points(0f, size.height.toFloat(), density, inverted = true)?.reversed()
        if (yLabels != null && yPoints != null) Column(
            modifier = Modifier.fillMaxHeight().padding(end = 8.dp, bottom = with(density) { xSize.height.toDp() }),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.End
        ) {
            for (point in yPoints) Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                yAxis.markers?.label?.invoke(point.src)
            }
        }

        val xAxis = graph.x
        val xLabels = xAxis?.markers?.label
        val xPoints = xAxis?.points(0f, size.width.toFloat(), density, inverted = false)
        Column(modifier = Modifier.weight(1f).fillMaxSize()) {
            Canvas(modifier = Modifier.weight(1f).fillMaxSize().onPlaced { size = it.size }) {
                if (yAxis != null && yPoints != null) drawYAxis(yAxis, yPoints)
                if (xAxis != null && xPoints != null) drawXAxis(xAxis, xPoints)
                plot(graph)
            }
            if (xLabels != null && xPoints != null) Row(
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp).onPlaced { xSize = it.size },
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val angle = xAxis.markers?.angle ?: 0f
                for (point in xPoints) Box(modifier = Modifier.weight(1f).rotate(angle).padding(if (xAxis.markers?.length == 0.dp) 0.dp else 8.dp)) {
                    xAxis.markers?.label?.invoke(point.src)
                }
            }
        }
    }
}