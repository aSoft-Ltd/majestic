package majestic.users.table.header

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import org.jetbrains.compose.resources.painterResource

data class NameCellColors(
    val surfaceContra: Color,
    val dominantActual: Color
)

@Composable
internal fun NameCell(
    fullName: String,
    resource: DrawableResource?,
    colors: NameCellColors,
    modifier: Modifier = Modifier
) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.spacedBy(10.dp),
    verticalAlignment = Alignment.CenterVertically
) {
    if (resource != null) Image(
        modifier = Modifier.size(40.dp).clip(RoundedCornerShape(5.dp)),
        painter = painterResource(resource),
        contentDescription = null,
    )
    else Box(
        modifier = Modifier
            .size(32.dp)
            .background(color = colors.dominantActual, shape = CircleShape)
            .padding(10.dp)
    ) {
        Text(
            text = fullName,
            color = colors.surfaceContra,
            fontSize = 16.sp
        )
    }
    Text(
        text = fullName,
        lineHeight = 1.sp,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        color = colors.surfaceContra
    )
}