package majestic.graph.bazier.tools

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.PathMeasure
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import majestic.graph.bazier.LineChartConfig
import majestic.graph.bazier.utils.ChartLayoutInfo
import majestic.graph.bazier.utils.buildBezierPath

 internal fun DrawScope.drawChartData(
    config: LineChartConfig,
    layout: ChartLayoutInfo,
    pathMeasure: PathMeasure,
    animationProgress: Float,
    yMin: Float,
    yRange: Float
) {
    config.series.forEach { series ->
        val points = series.values.mapIndexed { i, value ->
            val x = layout.chartLeft + (i.toFloat() / (series.values.size - 1)) * layout.chartWidth
            val y = layout.chartBottom - ((value - yMin) / yRange * layout.chartHeight)
            Offset(x, y)
        }
        val fullPath = buildBezierPath(points)
        pathMeasure.setPath(fullPath, false)
        val animatedPath = Path()
        pathMeasure.getSegment(
            0f,
            animationProgress * pathMeasure.length,
            animatedPath
        )

        val paint = Paint().apply {
            this.color = series.color.key
            this.style = PaintingStyle.Stroke
            this.strokeWidth = series.strokeWidth
            this.strokeCap = StrokeCap.Round
            this.strokeJoin = StrokeJoin.Round
            if (series.dashed) {
                this.pathEffect = PathEffect.dashPathEffect(config.dashIntervals, 0f)
            }
        }
        drawIntoCanvas { canvas ->
            canvas.drawPath(animatedPath, paint)
        }
    }
}
