package majestic.payments.invoice.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.payments.invoice.detail.tools.DetailRow
import majestic.payments.invoice.detail.tools.InvoiceDetail
import majestic.payments.invoice.detail.tools.InvoiceItemList
import majestic.payments.invoice.detail.tools.InvoiceTotal
import majestic.payments.invoice.tools.InvoiceColors
import majestic.payments.labels.invoice.InvoiceLabels

@Composable
fun InvoiceInfo(
    labels: InvoiceLabels,
    colors: InvoiceColors,
    orientation: ScreenOrientation,
    detail: InvoiceDetail,
    modifier: Modifier = Modifier
) = Column(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(20.dp),
    horizontalAlignment = Alignment.End
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.weight(2.4f),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = labels.address,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 1.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = colors.foreground.copy(0.7f)
            )
            Text(
                text = detail.address,
                fontSize = 14.sp,
                lineHeight = 18.sp,
                color = colors.foreground
            )
        }
        if (orientation is Landscape) Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            DetailRow(modifier = Modifier.fillMaxWidth(), label = labels.invoiceNo, description = detail.invoiceNo, color = colors.foreground)
            DetailRow(modifier = Modifier.fillMaxWidth(), label = labels.issued, description = detail.issued, color = colors.foreground)
            DetailRow(modifier = Modifier.fillMaxWidth(), label = labels.due, description = detail.due, color = colors.foreground)
        }
    }
    InvoiceItemList(
        labels = labels,
        colors = colors.header,
        orientation = orientation,
        items = detail.items,
        modifier = Modifier.fillMaxWidth()
    )
    Column(
        modifier = Modifier.width(if (orientation is Landscape) 300.dp else 200.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        DetailRow(modifier = Modifier.fillMaxWidth(), label = labels.subTotal, description = detail.subTotal, color = colors.foreground)
        DetailRow(modifier = Modifier.fillMaxWidth(), label = labels.tax, description = detail.tax, color = colors.foreground)
        DetailRow(modifier = Modifier.fillMaxWidth(), label = "${labels.disc}%", description = detail.discount, color = colors.foreground)
    }
    InvoiceTotal(
        modifier = Modifier.then(
            if (orientation is Portrait) Modifier.fillMaxWidth()
            else Modifier.width(310.dp).clip(RoundedCornerShape(10.dp))
        )
            .background(colors.total)
            .padding(10.dp),
        label = labels.total,
        amount = detail.total,
        color = colors.foreground,
        orientation = orientation
    )
}
