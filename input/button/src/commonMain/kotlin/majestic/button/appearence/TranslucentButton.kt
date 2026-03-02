package majestic.button.appearence

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import majestic.ColorPair
import majestic.button.ButtonParams
import majestic.button.button
import majestic.button.toButtonParams

@Composable
fun Modifier.translucentButton(
    color: Color,
    enabled: Boolean = true,
    shape: Shape = RoundedCornerShape(percent = 50),
    source: MutableInteractionSource = remember { MutableInteractionSource() },
    alpha: Float = 0.07f,
    onClick: () -> Unit = { },
): Modifier {
    val hoveredAlpha = alpha + 0.02f
    val disabledAlpha = maxOf(alpha - 0.04f, 0.01f)

    val alphaState = ButtonParams(alpha, hoveredAlpha, hoveredAlpha, disabledAlpha)
    val pair = ColorPair(foreground = color, background = Color.Transparent)

    return this
        .button(
            colors = pair.toButtonParams(),
            source = source,
            shape = shape,
            disabled = !enabled,
            onClick = onClick
        )
        .hoverOverlay(
            tint = color,
            alpha = alphaState,
            source = source,
            disabled = !enabled,
            shape = shape
        )
}