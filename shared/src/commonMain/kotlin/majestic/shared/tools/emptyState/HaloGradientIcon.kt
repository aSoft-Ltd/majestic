package majestic.shared.tools.emptyState

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import dev.chrisbanes.haze.HazeDefaults
import dev.chrisbanes.haze.HazeTint
import dev.chrisbanes.haze.hazeEffect
import majestic.tools.withHardLight
import majestic.tools.withScreen
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.vectorResource

@Composable
internal fun HaloGradientIcon(
    icon: DrawableResource,
    colors: EmptyStateColors,
    modifier: Modifier = Modifier
) = BoxWithConstraints(
    modifier = modifier,
    contentAlignment = Alignment.Center
) {
    val diameter = minOf(maxWidth, maxHeight)
    val ellipseSize = diameter * (73f / 86f)
    val glowBlurRadius = diameter * (50f / 86f)
    val screen = colors.iconBackdrop.withScreen(colors.iconBlur, 1f)

    // Omit the glow where blur is unavailable rather than render a hard-edged fallback ellipse.
    if (HazeDefaults.blurEnabled()) {
        Canvas(
            modifier = Modifier
                // Leave three blur radii on each side to preserve the soft tail.
                .requiredSize(ellipseSize + glowBlurRadius * 6)
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
            drawCircle(
                color = screen.copy(alpha = 0.2f),
                radius = ellipseSize.toPx() / 2f
            )
        }
    }

    Canvas(modifier = Modifier.matchParentSize()) {
        val scale = size.minDimension / 86f
        val hardLight = colors.iconBackdrop.withHardLight(colors.iconBlur, 1f)
        val frameRadius = 87.09f / 2f * scale
        drawCircle(
            brush = Brush.horizontalGradient(
                colors = listOf(hardLight.copy(alpha = 0.4f), hardLight.copy(alpha = 0f)),
                startX = center.x - frameRadius,
                endX = center.x
            ),
            radius = frameRadius
        )
    }
    Image(
        imageVector = vectorResource(icon),
        contentDescription = null,
        colorFilter = ColorFilter.tint(colors.iconBlur),
        modifier = Modifier.fillMaxSize((87.09f - 19.55f * 2f) / 86f)
    )
}