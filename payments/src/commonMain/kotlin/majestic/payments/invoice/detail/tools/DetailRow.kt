package majestic.payments.invoice.detail.tools

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp

@Composable
internal fun DetailRow(
    label: String,
    description: String,
    color: Color,
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
    Text(
        text = description,
        fontSize = 14.sp,
        lineHeight = 1.sp,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        color = color
    )
}
