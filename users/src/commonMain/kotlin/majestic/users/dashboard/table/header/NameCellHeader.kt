package majestic.users.dashboard.table.header

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.vectorResource

data class NameCellHeaderColors(
    val avatar: Color,
    val label: Color
)

@Composable
internal fun NameCellHeader(
    modifier: Modifier,
    colors: NameCellHeaderColors,
    label: String,
    avatar: DrawableResource
) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.spacedBy(10.dp),
    verticalAlignment = Alignment.CenterVertically
) {
    Icon(
        modifier = Modifier.size(32.dp).clip(RoundedCornerShape(5.dp)),
        imageVector = vectorResource(avatar),
        tint = colors.avatar,
        contentDescription = null,
    )

    Text(
        text = label,
        lineHeight = 1.sp,
        maxLines = 1,
        fontSize = 15.sp,
        overflow = TextOverflow.Ellipsis,
        color = colors.label
    )
}