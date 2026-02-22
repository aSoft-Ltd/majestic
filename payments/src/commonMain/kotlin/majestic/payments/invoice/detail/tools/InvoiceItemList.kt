package majestic.payments.invoice.detail.tools

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.ColorPair
import majestic.payments.labels.invoice.InvoiceLabels
import majestic.tooling.DrawDirection
import majestic.tooling.separator

@Composable
internal fun InvoiceItemList(
    labels: InvoiceLabels,
    colors: ColorPair,
    orientation: ScreenOrientation,
    items: List<InvoiceItem>,
    modifier: Modifier = Modifier
) = Column(modifier = modifier) {
    Row(
        modifier = Modifier.separator(color = colors.foreground.copy(0.05f))
            .separator(color = colors.foreground.copy(0.05f), direction = DrawDirection.TOP)
            .background(if (orientation is Portrait) colors.background else Color.Transparent)
            .padding(if (orientation is Landscape) 10.dp else 5.dp)
    ) {
        if (orientation is Landscape) Text(
            modifier = Modifier.weight(2f),
            text = labels.number,
            fontWeight = FontWeight.Bold,
            color = colors.foreground,
            fontSize = 14.sp
        )
        Text(
            modifier = Modifier.weight(6f),
            text = labels.item,
            fontWeight = FontWeight.Bold,
            color = colors.foreground,
            fontSize = 14.sp
        )
        Text(
            modifier = Modifier.weight(4f),
            text = labels.rate,
            fontWeight = FontWeight.Bold,
            color = colors.foreground,
            fontSize = 14.sp,
            textAlign = TextAlign.End
        )
        Text(
            modifier = Modifier.weight(4f),
            text = labels.qty,
            fontWeight = FontWeight.Bold,
            color = colors.foreground,
            fontSize = 14.sp,
            textAlign = TextAlign.End
        )
        Text(
            modifier = Modifier.weight(6f),
            text = labels.total,
            fontWeight = FontWeight.Bold,
            color = colors.foreground,
            fontSize = 14.sp,
            textAlign = TextAlign.End
        )
    }

    for (item in items) Row(
        modifier = Modifier.separator(color = colors.foreground.copy(0.05f), isLast = item != items.last())
            .padding(vertical = 5.dp, horizontal = 10.dp)
    ) {
        if (orientation is Landscape) Text(
            modifier = Modifier.weight(2f),
            text = "${item.number}",
            color = colors.foreground,
            fontSize = 12.sp,
            lineHeight = 14.sp
        )
        Text(
            modifier = Modifier.weight(6f),
            text = item.name,
            color = colors.foreground,
            fontSize = 12.sp,
            lineHeight = 14.sp
        )
        Text(
            modifier = Modifier.weight(4f),
            text = item.rate,
            color = colors.foreground,
            fontSize = 12.sp,
            lineHeight = 14.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.End
        )
        Text(
            modifier = Modifier.weight(4f),
            text = "${item.qty}",
            color = colors.foreground,
            fontSize = 12.sp,
            lineHeight = 14.sp,
            textAlign = TextAlign.End
        )
        Text(
            modifier = Modifier.weight(6f),
            text = item.total,
            color = colors.foreground,
            fontSize = 12.sp,
            lineHeight = 14.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.End
        )
    }
}
