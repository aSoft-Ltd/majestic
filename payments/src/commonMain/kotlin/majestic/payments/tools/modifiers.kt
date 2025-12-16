package majestic.payments.tools

import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import majestic.Light
import majestic.ThemeColor

internal fun Modifier.addIcon(
    theme: ThemeColor,
    iconInteraction: MutableInteractionSource,
    iconRotation: Float
) = clip(CircleShape)
    .background(if (theme is Light) theme.dominant.actual.color else theme.surface.contra.color)
    .pointerHoverIcon(PointerIcon.Hand).hoverable(interactionSource = iconInteraction)
    .rotate(iconRotation)
    .padding(10.dp)

internal fun Modifier.separator(color: Color, width: Dp = 1.dp) = drawBehind {
    val strokeWidth = width.toPx()
    drawLine(
        color = color,
        start = Offset(0f, size.height + strokeWidth / 2),
        end = Offset(size.width, size.height + strokeWidth / 2),
        strokeWidth = strokeWidth
    )
}
