package majestic.graph.tools.plot

import androidx.compose.ui.graphics.drawscope.DrawScope
import majestic.GraphBuilderScope
import majestic.graph.LinePlot
import majestic.graph.tools.Projected
import majestic.graph.tools.YProjection

internal fun DrawScope.plot(graph: GraphBuilderScope, x: Projected, y: YProjection) {
    for (p in graph.plots) when (p) {
        is LinePlot -> plot(p, x, y)
    }
}