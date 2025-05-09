package majestic

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import majestic.colors.ColorPair

data class ToggleSwitchColors(
    val on: ColorPair = ColorPair(
        background = Color(0xFF4196F0),
        foreground = Color.White
    ),
    val off: ColorPair = ColorPair(
        background = Color(0xFFF2F4F7),
        foreground = Color.White
    )
)

@Composable
fun ToggleSwitch(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    height: Dp = 30.dp,
    circleSize: Dp = 20.dp,
    width: Dp = 60.dp,
    circlePadding: Dp = 4.dp,
    shape: Shape = CircleShape,
    colors: ToggleSwitchColors = ToggleSwitchColors(),
) {
    val sizePx = with(LocalDensity.current) { (width - height - (circlePadding * 2)).toPx() }
    val animateTranslation by animateFloatAsState(
        targetValue = if (checked) sizePx else 0f,
        animationSpec = tween(durationMillis = 300)
    )
    val color = if (checked) colors.on else colors.off
    Row(
        modifier = Modifier
            .height(height)
            .width(width)
            .clip(RoundedCornerShape(height))
            .pointerHoverIcon(PointerIcon.Hand)
            .clickable(
                interactionSource = NoRippleInteractionSource,
                indication = null,
                enabled = true,
                onClick = { onCheckedChange?.invoke(!checked) }
            ).background(color.background),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            Modifier
                .graphicsLayer { translationX = animateTranslation }
                .padding(circlePadding)
                .size(circleSize)
                .clip(shape)
                .background(color.foreground)
        )
    }
}