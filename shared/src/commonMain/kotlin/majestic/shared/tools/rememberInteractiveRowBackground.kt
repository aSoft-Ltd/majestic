package majestic.shared.tools

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color

@Composable
fun rememberInteractiveRowBackground(
    isActive: Boolean,
    active: Color,
    activeHovered: Color,
    interactionSource: MutableInteractionSource,
    defaultColor: Color = Color.Transparent,
    duration: Int = 200
): Color {
    val isHovered by interactionSource.collectIsHoveredAsState()

    val safeDefaultColor = if (defaultColor == Color.Transparent) {
        active.copy(alpha = 0f)
    }
    else {
        defaultColor
    }

    val targetColor = when {
        isActive && isHovered -> activeHovered
        isActive || isHovered -> active
        else -> safeDefaultColor
    }

    val animatedBackground by animateColorAsState(
        targetValue = targetColor,
        animationSpec = tween(duration, easing = FastOutSlowInEasing),
        label = "InteractiveRowBackgroundAnimation"
    )

    return animatedBackground
}