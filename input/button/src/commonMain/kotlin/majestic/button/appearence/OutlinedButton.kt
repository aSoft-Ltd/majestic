package majestic.button.appearence

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import majestic.ColorPair
import majestic.button.ButtonParams
import majestic.button.Stroke
import majestic.button.button
import majestic.button.toButtonParams

@Composable
fun Modifier.outlinedButton(
    color: Color,
    enabled: Boolean = true,
    shape: Shape = RoundedCornerShape(percent = 50),
    source: MutableInteractionSource = remember { MutableInteractionSource() },
    onClick: () -> Unit = { },
): Modifier {
    val borderColor = color.copy(alpha = 0.1f)
    val outline = ButtonParams(
        default = Stroke(borderColor, 1.dp),
        hovered = Stroke(borderColor, 1.dp),
        pressed = Stroke(borderColor, 1.dp),
        disabled = Stroke(borderColor.copy(alpha = 0.4f), 1.dp)
    )

    val alphaState = ButtonParams(0f, 0.05f, 0.05f, 0f)
    val pair = ColorPair(foreground = color, background = Color.Transparent)

    return this
        .button(
            colors = pair.toButtonParams(),
            outline = outline,
            source = source,
            shape = shape,
            disabled = !enabled,
            onClick = onClick
        ).hoverOverlay(
            tint = color,
            alpha = alphaState,
            source = source,
            disabled = !enabled,
            shape = shape
        )
}