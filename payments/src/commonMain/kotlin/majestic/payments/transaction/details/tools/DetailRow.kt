package majestic.payments.transaction.details.tools

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun DetailRow(
    label: String,
    description: String,
    color: Color,
    image: DrawableResource? = null,
    modifier: Modifier = Modifier
) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween
) {
    Text(
        text = label,
        fontSize = 14.sp,
        lineHeight = 1.sp,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        color = color.copy(0.4f)
    )
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = description,
            fontSize = 14.sp,
            lineHeight = 1.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = color
        )
        if (image != null) Image(
            modifier = Modifier.size(12.dp),
            painter = painterResource(image),
            contentDescription = null,
        )
    }
}
