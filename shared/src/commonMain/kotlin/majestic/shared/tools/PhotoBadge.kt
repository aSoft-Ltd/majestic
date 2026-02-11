package majestic.shared.tools

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun PhotoBadge(
    recipient: DrawableResource,
    payer: DrawableResource,
    size: Dp = 24.dp,
    shape: Shape = RoundedCornerShape(5.dp),
    modifier: Modifier = Modifier
) = Box(modifier = modifier) {
    Image(
        painter = painterResource(payer),
        contentDescription = null,
        modifier = Modifier.size(size).clip(shape)
    )
    Image(
        painter = painterResource(recipient),
        contentDescription = null,
        modifier = Modifier
            .padding(start = size / 2, top = size / 2)
            .clip(shape)
            .size(size)
    )
}