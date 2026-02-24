package majestic.payments.invoice.table.tools

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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.payments.labels.PaymentStatusLabels
import majestic.payments.tools.PaymentStatus
import nation.Country
import nation.Flag
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun InvoiceItem(
    labels: PaymentStatusLabels,
    name: String,
    reference: String,
    amount: String,
    status: PaymentStatus,
    country: Country,
    avatar: DrawableResource,
    color: Color,
    modifier: Modifier = Modifier
) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(12.dp)
) {
    Image(
        painter = painterResource(avatar),
        contentDescription = "Avatar",
        contentScale = ContentScale.Crop,
        modifier = Modifier.size(35.dp).clip(CircleShape)
    )
    Column(verticalArrangement = Arrangement.spacedBy(space = 6.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(space = 4.dp)
        ) {
            Text(
                text = name,
                color = color,
                fontSize = 14.sp,
                lineHeight = 1.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Flag(
                country = country,
                scale = ContentScale.Crop,
                modifier = Modifier.size(14.dp).clip(CircleShape)
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "$reference • $amount/=",
                color = color.copy(0.4f),
                fontSize = 12.sp,
                lineHeight = 1.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = " • ",
                color = color.copy(0.4f),
                fontSize = 12.sp,
                lineHeight = 1.sp
            )
            Text(
                text = status.getLabel(labels),
                color = status.getColor(),
                fontSize = 12.sp,
                lineHeight = 1.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
