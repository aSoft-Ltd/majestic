package majestic.payments.wallet.tools

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import majestic.ColorPair
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

internal data class AvatarOverflow(
    val size: Dp,
    val fontSize: TextUnit,
    val color: ColorPair,
    val shape: Shape
) {
    companion object {
        val Default = AvatarOverflow(
            size = 20.dp,
            fontSize = 10.sp,
            shape = CircleShape,
            color = ColorPair(
                background = Color.Blue,
                foreground = Color.White
            )
        )
    }
}

@Composable
internal fun Avatar(
    color: Color,
    images: List<DrawableResource>,
    size: Dp = 30.dp,
    border: Dp = 2.dp,
    shape: Shape = CircleShape,
    overlapPercent: Float = 0.3f,
    maxVisible: Int = 3,
    overflow: AvatarOverflow = AvatarOverflow.Default,
    modifier: Modifier = Modifier
) {
    if (images.isEmpty()) return
    val overlapOffset = size * (1 - overlapPercent)

    Box(modifier = modifier, contentAlignment = Alignment.CenterStart) {
        images.take(maxVisible).forEachIndexed { index, avatar ->
            Image(
                painter = painterResource(avatar),
                contentDescription = "Student avatar ${index + 1}",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(size)
                    .offset(x = overlapOffset * index)
                    .zIndex(images.size.toFloat() - index)
                    .clip(shape)
                    .border(width = border, color = color, shape = shape)
            )
        }
        if (images.size > maxVisible) Box(
            modifier = Modifier.size(overflow.size)
                .offset(x = overlapOffset * (maxVisible + 0.2f))
                .zIndex(maxVisible.toFloat())
                .clip(overflow.shape)
                .background(color = overflow.color.background)
                .border(width = border, color = color, shape = overflow.shape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "+${images.size - maxVisible}",
                color = overflow.color.foreground,
                lineHeight = 1.sp,
                fontSize = overflow.fontSize,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
