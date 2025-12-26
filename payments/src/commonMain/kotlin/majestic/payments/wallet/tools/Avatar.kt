package majestic.payments.wallet.tools

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun Avatar(
    color: Color,
    images: List<DrawableResource>,
    size: Dp = 30.dp,
    border: Dp = 2.dp,
    shape: Shape = CircleShape,
    overlapPercent: Float = 0.3f,
    modifier: Modifier = Modifier
) {
    if (images.isEmpty()) return
    val overlapOffset = size * (1 - overlapPercent)

    Box(modifier = modifier, contentAlignment = Alignment.CenterStart) {
        images.forEachIndexed { index, avatar ->
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
    }
}
