package majestic

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

@Composable
fun AvatarStack(
    painters: List<Painter>,
    modifier: Modifier = Modifier,
    avatarSize: Dp = 32.dp,
    overlapFraction: Float = 0.5f,
    maxVisible: Int = 3,
    borderColor: Color = Color.White,
    borderWidth: Dp = 2.dp,
    overflowTextColor: Color = Color.White,
    overflowTextSize: TextUnit = 14.sp,
    overflowSpacing: Dp = 16.dp
) {
    val overlapPx = (avatarSize * overlapFraction).value.toInt()
    val spacingPx = with(LocalDensity.current) { overflowSpacing.toPx().roundToInt() }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        painters.take(maxVisible).forEachIndexed { index, painter ->
            Box(
                modifier = Modifier
                    .offset { IntOffset(x = -overlapPx * index, y = 0) }
                    .size(avatarSize)
                    .clip(CircleShape)
                    .border(borderWidth, borderColor, CircleShape)
            ) {
                Image(
                    painter = painter,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.matchParentSize()
                )
            }
        }
        if (painters.size > maxVisible) {
            Box(
                modifier = Modifier
                    .offset { IntOffset(x = -overlapPx * maxVisible + spacingPx, y = 0) }
                    .wrapContentSize(Alignment.Center)
            ) {
                Text(
                    text = "+${painters.size - maxVisible}",
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    color = overflowTextColor,
                    fontSize = overflowTextSize
                )
            }
        }
    }
}