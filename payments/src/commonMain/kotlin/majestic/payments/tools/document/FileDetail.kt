package majestic.payments.tools.document

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun FileDetail(
    name: String,
    description: String,
    thumbnail: DrawableResource,
    color: Color,
    modifier: Modifier = Modifier
) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
) {
    Column(
        modifier = Modifier.weight(1f),
        verticalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        Text(
            text = description,
            fontSize = 14.sp,
            lineHeight = 1.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = color
        )
        Text(
            text = name,
            fontSize = 10.sp,
            lineHeight = 1.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = color.copy(0.4f)
        )
    }
    Image(
        modifier = Modifier.size(30.dp)
            .clip(RoundedCornerShape(2.dp)),
        painter = painterResource(thumbnail),
        contentDescription = null,
        contentScale = ContentScale.Crop
    )
}
