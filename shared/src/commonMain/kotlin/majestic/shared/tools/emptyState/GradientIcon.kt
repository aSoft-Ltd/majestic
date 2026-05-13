package majestic.shared.tools.emptyState

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.vectorResource

@Composable
internal fun GradientIcon(icon: DrawableResource, colors: EmptyStateColors, modifier: Modifier = Modifier) =
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        val ellipseSize = 56.dp
        val blurRadius = 32.dp
        val blurLayerSize = ellipseSize + blurRadius * 6
        val blurAlpha = 0.3f

        Box(
            modifier = Modifier
                .requiredSize(blurLayerSize)
                .offset(x = 0.dp, y = (-4).dp)
                .graphicsLayer {
                    compositingStrategy = CompositingStrategy.Offscreen
                }
                .blur(
                    radius = blurRadius,
                    edgeTreatment = BlurredEdgeTreatment.Unbounded
                ),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(ellipseSize)
                    .background(
                        color = colors.iconBlur.copy(alpha = blurAlpha),
                        shape = CircleShape
                    )
            )
        }

        Image(
            imageVector = vectorResource(icon),
            contentDescription = null,
            modifier = Modifier
                .size(83.dp)
                .graphicsLayer {
                    compositingStrategy = CompositingStrategy.Offscreen
                }
                .drawWithContent {
                    drawContent()

                    drawRect(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                colors.gradient.first,
                                colors.gradient.second
                            ),
                            start = Offset(0f, 0f),
                            end = Offset(size.width, size.height)
                        ),
                        blendMode = BlendMode.SrcIn
                    )
                }
        )
    }