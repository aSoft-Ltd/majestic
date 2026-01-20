package majestic.payments.wallet.table.tools

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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.payments.tools.table.TableColors
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun CreatedCell(
    title: String,
    datetime: String,
    colors: TableColors,
    image: DrawableResource,
    modifier: Modifier = Modifier
) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(10.dp)
) {
    Image(
        modifier = Modifier.size(32.dp).clip(CircleShape),
        painter = painterResource(image),
        contentScale = ContentScale.Crop,
        contentDescription = null,
    )
    Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
        Text(
            text = title,
            color = colors.foreground,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = 14.sp,
            lineHeight = 1.sp
        )
        Text(
            text = datetime,
            color = colors.foreground.copy(0.5f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = 10.sp,
            lineHeight = 1.sp
        )
    }
}
