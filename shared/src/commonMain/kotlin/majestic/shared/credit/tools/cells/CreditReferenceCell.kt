package majestic.shared.credit.tools.cells

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import majestic.shared.credit.tools.CreditTableProps

@Composable
fun CreditReferenceCell(
    invoiceRef: String,
    txnRef: String,
    props: CreditTableProps,
    modifier: Modifier = Modifier
) = Column(modifier = modifier) {
    val txtColor = props.colors.surfaceColor.foreground
    Text(text = invoiceRef, color = txtColor, fontSize = 15.sp)
    Text(text = txnRef, color = txtColor.copy(alpha = 0.6f), fontSize = 13.sp)
}