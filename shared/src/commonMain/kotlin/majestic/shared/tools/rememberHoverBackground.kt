package majestic.shared.tools

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import majestic.tools.withOverlay

@Composable
fun rememberHoverBackground(
    background: Color,
    foreground: Color,
    alpha: Float = 0.1f,
    duration: Int = 300
): Pair<Color, MutableInteractionSource> {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    val backgroundAlpha by animateFloatAsState(
        targetValue = if (isHovered) alpha else 0f,
        animationSpec = tween(duration, easing = FastOutSlowInEasing)
    )

    val animatedBackground = background.withOverlay(foreground, backgroundAlpha)

    return animatedBackground to interactionSource
}