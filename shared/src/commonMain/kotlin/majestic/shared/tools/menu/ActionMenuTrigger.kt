package majestic.shared.tools.menu

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import majestic.ColorPair
import majestic.button.ButtonParams
import majestic.button.button
import majestic.button.toResolved

@Composable
internal fun Modifier.listItemIconButton(
    colors: ListIconButtonColors,
    disabled: Boolean = false,
    shape: Shape = RoundedCornerShape(percent = 50),
    source: MutableInteractionSource = remember { MutableInteractionSource() },
    onClick: () -> Unit = { },
): Modifier {
    val alphaState = ButtonParams(0f, 0.09f, 0.09f, 0.07f * 0.4f)
    val pair = ColorPair(foreground = colors.foreground, background = Color.Transparent)
    return button(
        colors = pair.toButtonParams(),
        source = source,
        shape = shape,
        disabled = disabled,
        onClick = onClick
    )
        .hoverOverlay(
            tint = colors.tint,
            alpha = alphaState,
            source = source,
            disabled = disabled,
            shape = shape
        )
}

internal fun ColorPair.toButtonParams(): ButtonParams<ColorPair> {
    val pair = ColorPair(background = background, foreground = foreground)
    return ButtonParams(
        default = pair,
        hovered = pair,
        pressed = pair,
        disabled = ColorPair(
            background = background.copy(alpha = if (background == Color.Transparent) 1f else 0.4f),
            foreground = foreground.copy(alpha = 0.4f)
        )
    )
}

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
        animationSpec = tween(300, easing = FastOutSlowInEasing),
        label = "hoverOverlayAlpha"
    )

    return clip(shape).drawWithContent {
        drawContent()
        if (animatedAlpha > 0f) {
            drawRect(color = tint.copy(alpha = animatedAlpha))
        }
    }
}