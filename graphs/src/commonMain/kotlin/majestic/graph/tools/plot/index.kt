package majestic.graph.tools.plot

import androidx.compose.ui.graphics.drawscope.DrawScope
import majestic.GraphBuilderScope
import majestic.graph.LinePlot

internal fun DrawScope.plot(graph: GraphBuilderScope) {
    for (p in graph.plots) when (p) {
        is LinePlot -> plot(p, x = graph.x?.max ?: 100f, y = graph.y?.max ?: 100f)
    }
}