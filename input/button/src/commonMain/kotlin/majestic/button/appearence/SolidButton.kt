package majestic.button.appearence

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import majestic.ColorPair
import majestic.button.button
import majestic.button.toButtonParams

@Composable
fun Modifier.solidButton(
    colors: ColorPair,
    enabled: Boolean = true,
    expandable: Boolean = false,
    shape: Shape = RoundedCornerShape(percent = 50),
    source: MutableInteractionSource = remember { MutableInteractionSource() },
    onClick: () -> Unit = { },
): Modifier {
    val hovered by source.collectIsHoveredAsState()
    val targetAlpha = if (hovered && enabled && !expandable) 0.8f else 1f
    val animatedAlpha by animateFloatAsState(
        targetValue = targetAlpha,
        animationSpec = tween(450, easing = FastOutSlowInEasing),
        label = "solidButtonAlpha"
    )

    return this
        .button(
            colors = colors.toButtonParams(),
            source = source,
            shape = shape,
            disabled = !enabled,
            onClick = onClick
        )
        .graphicsLayer { alpha = animatedAlpha }
}