package majestic.payments.tools.table

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun CommonCell(
    color: Color,
    title: String,
    avatar: DrawableResource? = null,
    avatarShape: Shape = CircleShape,
    modifier: Modifier = Modifier,
    subtitle: @Composable () -> Unit = {}
) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(10.dp)
) {
    if (avatar != null) Image(
        modifier = Modifier.size(32.dp).clip(avatarShape),
        painter = painterResource(avatar),
        contentScale = ContentScale.Crop,
        contentDescription = null,
    )
    Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
        Text(
            text = title,
            color = color,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = 14.sp,
            lineHeight = 1.sp
        )
        subtitle()
    }
}

@Composable
internal fun CommonCell(
    color: Color,
    title: String,
    subtitle: String,
    avatar: DrawableResource? = null,
    avatarShape: Shape = CircleShape,
    modifier: Modifier = Modifier
) = CommonCell(
    color = color,
    title = title,
    avatar = avatar,
    avatarShape = avatarShape,
    modifier = modifier
) {
    Text(
        text = subtitle,
        color = color.copy(0.3f),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        fontSize = 10.sp,
        lineHeight = 1.sp
    )
}
