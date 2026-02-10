package majestic.shared.credit.tools

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.ScreenOrientation
import majestic.shared.credit.CreditUsage
import majestic.shared.credit.tools.cells.CreditActorCell
import majestic.shared.credit.tools.cells.CreditPurchasedCell
import majestic.shared.credit.tools.cells.CreditReferenceCell
import majestic.shared.menu.MenuOption
import majestic.shared.menu.OptionMenu

@Composable
fun <T> CreditRow(
    credit: CreditUsage,
    weights: CreditTableWeights,
    colors: CreditTableColors,
    actions: List<OptionMenu<T>>,
    orientation: ScreenOrientation,
    modifier: Modifier = Modifier
) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically
) {
    CreditActorCell(
        logo = credit.recipientLogo,
        name = credit.recipientName,
        id = credit.recipientId,
        colors = colors,
        modifier = Modifier.weight(weights.recipient)
    )
    CreditActorCell(
        logo = credit.payerLogo,
        name = credit.payerName,
        id = credit.payerId,
        colors = colors,
        modifier = Modifier.weight(weights.payer)
    )
    CreditReferenceCell(
        invoiceRef = credit.invoiceRef,
        txnRef = credit.txnRef,
        colors = colors,
        modifier = Modifier.weight(weights.ref)
    )
    CreditPurchasedCell(
        credit = credit,
        colors = colors,
        modifier = Modifier.weight(weights.purchased).padding(end = 16.dp)
    )
    Text(
        text = credit.amount,
        color = colors.surfaceColor.foreground,
        fontSize = 15.sp,
        modifier = Modifier.weight(weights.amount)
    )
    Box(modifier = Modifier.weight(weights.actions), contentAlignment = Alignment.CenterEnd) {
        MenuOption(
            colors = colors.menu,
            orientation = orientation,
            actions = actions,
        ) { action ->
        }
    }
}