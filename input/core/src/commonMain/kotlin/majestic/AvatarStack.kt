package majestic

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AvatarStack(
    painters: List<Painter>,
    modifier: Modifier = Modifier,
    avatarSize: Dp = 32.dp,
    overlapFraction: Float = 0.5f,
    maxVisible: Int = 4,
    borderColor: Color = Color.White,
    borderWidth: Dp = 2.dp,
    overflowTextColor: Color = Color.White,
    overflowTextSize: TextUnit = 14.sp,
    overflowGap: Dp = 4.dp
) {
    val showOverflow = painters.size > maxVisible
    val step = avatarSize * (1f - overlapFraction)

    Layout(
        modifier = modifier,
        content = {
            painters.take(maxVisible).forEach { painter ->
                Box(
                    modifier = Modifier
                        .size(avatarSize)
                        .clip(CircleShape)
                        .border(borderWidth, borderColor, CircleShape)
                ) {
                    Image(
                        painter = painter,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

            if (showOverflow) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(avatarSize)
                        .clip(CircleShape)
                ) {
                    Text(
                        text = "+${(painters.size - maxVisible).coerceAtLeast(0)}",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = overflowTextColor,
                        fontSize = overflowTextSize
                    )
                }
            }
        }
    ) { measurables, constraints ->
        val sizePx = avatarSize.roundToPx()
        val stepPx = step.roundToPx().coerceAtLeast(0)
        val gapPx = overflowGap.roundToPx()

        val placeable = measurables.map { it.measure(Constraints.fixed(sizePx, sizePx)) }
        val n = placeable.size

        val widthNeeded = when {
            n == 0 -> 0
            n == 1 -> sizePx
            else -> {
                val base = sizePx + (n - 1) * stepPx
                if (showOverflow) base + gapPx else base
            }
        }

        val width = widthNeeded.coerceIn(constraints.minWidth, constraints.maxWidth)
        val height = sizePx.coerceIn(constraints.minHeight, constraints.maxHeight)

        layout(width, height) {
            var x = 0
            placeable.forEachIndexed { index, p ->
                val isOverflowBubble = showOverflow && index == placeable.lastIndex
                if (isOverflowBubble) x += gapPx
                p.placeRelative(x, 0)
                x += stepPx
            }
        }
    }
}