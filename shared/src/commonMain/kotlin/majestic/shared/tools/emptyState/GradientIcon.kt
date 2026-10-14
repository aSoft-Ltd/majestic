package majestic.shared.tools.emptyState

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.VectorGroup
import androidx.compose.ui.graphics.vector.VectorPath
import androidx.compose.ui.unit.dp
import dev.chrisbanes.haze.HazeDefaults
import dev.chrisbanes.haze.HazeTint
import dev.chrisbanes.haze.hazeEffect
import majestic.tools.withScreen
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.vectorResource
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

@Composable
internal fun GradientIcon(
    icon: DrawableResource,
    colors: EmptyStateColors,
    modifier: Modifier = Modifier
) = BoxWithConstraints(
    modifier = modifier,
    contentAlignment = Alignment.Center
) {
    val diameter = minOf(maxWidth, maxHeight)
    val image = vectorResource(icon)
    val outline = remember(image, diameter) {
        ImageVector.Builder(
            name = image.name,
            defaultWidth = image.defaultWidth,
            defaultHeight = image.defaultHeight,
            viewportWidth = image.viewportWidth,
            viewportHeight = image.viewportHeight,
            autoMirror = image.autoMirror
        ).apply {
            addOutlineGroup(image.root, image.viewportWidth * 3f / diameter.value.coerceAtLeast(1f))
        }.build()
    }

    Image(
        imageVector = outline,
        contentDescription = null,
        modifier = Modifier
            .fillMaxSize()
            .graphicsLayer {
                compositingStrategy = CompositingStrategy.Offscreen
            }
            .drawWithCache {
                val angle = 146.57 * PI / 180.0
                val direction = Offset(sin(angle).toFloat(), -cos(angle).toFloat())
                val extent = (size.width * abs(direction.x) + size.height * abs(direction.y)) / 2f
                val center = Offset(size.width / 2f, size.height / 2f)
                val gradient = Brush.linearGradient(
                    0.0821f to colors.gradient.first,
                    0.8489f to colors.gradient.second,
                    start = center - direction * extent,
                    end = center + direction * extent
                )
                onDrawWithContent {
                    drawContent()
                    drawRect(brush = gradient, blendMode = BlendMode.SrcIn)
                }
            }
    )

    if (HazeDefaults.blurEnabled()) {
        val glowBlurRadius = 50.dp
        val glow = colors.iconBackdrop.withScreen(colors.iconBlur, 1f).copy(alpha = 0.2f)
        Canvas(
            modifier = Modifier
                .requiredSize(diameter + glowBlurRadius * 6)
                .hazeEffect {
                    blurRadius = glowBlurRadius
                    blurredEdgeTreatment = BlurredEdgeTreatment.Unbounded
                    backgroundColor = Color.Transparent
                    tints = listOf(HazeTint(Color.Transparent))
                    fallbackTint = HazeTint(Color.Transparent)
                    noiseFactor = 0f
                    drawContentBehind = false
                }
        ) {
            drawCircle(color = glow, radius = diameter.toPx() / 2f)
        }
    }
}

private fun ImageVector.Builder.addOutlineGroup(group: VectorGroup, strokeWidth: Float) {
    addGroup(
        name = group.name,
        rotate = group.rotation,
        pivotX = group.pivotX,
        pivotY = group.pivotY,
        scaleX = group.scaleX,
        scaleY = group.scaleY,
        translationX = group.translationX,
        translationY = group.translationY,
        clipPathData = group.clipPathData
    )
    for (node in group) {
        when (node) {
            is VectorGroup -> addOutlineGroup(node, strokeWidth)
            is VectorPath -> addPath(
                pathData = node.pathData,
                pathFillType = node.pathFillType,
                name = node.name,
                fill = node.fill,
                fillAlpha = node.fillAlpha,
                stroke = node.stroke,
                strokeAlpha = node.strokeAlpha,
                strokeLineWidth = if (node.strokeLineWidth > 0f) strokeWidth else 0f,
                strokeLineCap = node.strokeLineCap,
                strokeLineJoin = node.strokeLineJoin,
                strokeLineMiter = node.strokeLineMiter,
                trimPathStart = node.trimPathStart,
                trimPathEnd = node.trimPathEnd,
                trimPathOffset = node.trimPathOffset
            )
        }
    }
    clearGroup()
}