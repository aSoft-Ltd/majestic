package majestic.payments.transaction.table.tools

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun TransactionItem(
    name: String,
    code: String,
    account: String,
    amount: String,
    avatar: DrawableResource,
    color: Color,
    modifier: Modifier = Modifier
) = Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(space = 6.dp)) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        Image(
            painter = painterResource(avatar),
            contentDescription = "Avatar",
            modifier = Modifier.size(12.dp).clip(CircleShape)
        )
        Text(
            text = name,
            color = color,
            fontSize = 14.sp,
            lineHeight = 1.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = " • ",
            color = color.copy(0.3f),
            fontSize = 14.sp,
            lineHeight = 1.sp
        )
        Text(
            text = code,
            color = color.copy(0.3f),
            fontSize = 14.sp,
            lineHeight = 1.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }

    Row {
        Canvas(modifier = Modifier.width(24.dp).height(12.dp)) {
            val strokeWidth = 3f
            drawLine(
                color = color.copy(0.3f),
                start = Offset(size.width / 4, -10f),
                end = Offset(size.width / 4, size.height),
                strokeWidth = strokeWidth
            )
            drawLine(
                color = color.copy(0.3f),
                start = Offset(size.width / 4, size.height),
                end = Offset(size.width, size.height),
                strokeWidth = strokeWidth
            )
        }
        Row(modifier = Modifier.padding(2.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = account,
                color = color,
                fontSize = 12.sp,
                lineHeight = 1.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = " • ",
                color = color.copy(0.3f),
                fontSize = 14.sp,
                lineHeight = 1.sp,
            )
            Text(
                text = "TZS $amount",
                color = color.copy(0.3f),
                fontSize = 12.sp,
                lineHeight = 1.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
