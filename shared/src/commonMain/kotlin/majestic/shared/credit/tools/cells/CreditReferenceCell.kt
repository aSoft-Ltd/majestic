package majestic.shared.credit.tools.cells

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.shared.credit.tools.CreditTableColors

@Composable
fun CreditReferenceCell(
    invoiceRef: String,
    txnRef: String,
    colors: CreditTableColors,
    modifier: Modifier = Modifier,
) = Column(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(space = 0.dp, alignment = Alignment.CenterVertically)
) {
    val txtColor = colors.surfaceColor.foreground
    Text(text = invoiceRef, color = txtColor, fontSize = 14.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
    Text(text = txnRef, color = txtColor.copy(alpha = 0.6f), fontSize = 12.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
}