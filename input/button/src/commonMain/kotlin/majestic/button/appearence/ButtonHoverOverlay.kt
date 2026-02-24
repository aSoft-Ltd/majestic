package majestic.button.appearence

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import majestic.button.ButtonParams
import majestic.button.toResolved

@Composable
internal fun Modifier.hoverOverlay(
    tint: Color,
    alpha: ButtonParams<Float>,
    source: MutableInteractionSource,
    disabled: Boolean,
    shape: Shape
): Modifier {
    val hovered by source.collectIsHoveredAsState()
    val pressed by source.collectIsPressedAsState()

    val targetAlpha = alpha.toResolved(hovered, pressed, disabled)
    val animatedAlpha by animateFloatAsState(
        targetValue = targetAlpha,
        animationSpec = tween(450, easing = FastOutSlowInEasing),
        label = "hoverOverlayAlpha"
    )

    return clip(shape).drawWithContent {
        drawContent()
        if (animatedAlpha > 0f) {
            drawRect(color = tint.copy(alpha = animatedAlpha))
        }
    }
}