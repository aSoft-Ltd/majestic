package majestic.shared.credit.tools.cells

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import majestic.ThemeColor

@Composable
fun CreditReferenceCell(
    invoiceRef: String,
    txnRef: String,
    theme: ThemeColor,
    modifier: Modifier = Modifier
) = Column(modifier = modifier) {
    Text(text = invoiceRef, color = theme.surface.contra.color, fontSize = 15.sp)
    Text(text = txnRef, color = theme.surface.contra.color.copy(alpha = 0.6f), fontSize = 13.sp)
}