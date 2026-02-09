package majestic.shared.credit.tools

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import composex.screen.orientation.ScreenOrientation
import majestic.ThemeColor
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
    theme: ThemeColor,
    props: CreditTableProps,
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
        theme = theme,
        modifier = Modifier.weight(weights.recipient)
    )
    CreditActorCell(
        logo = credit.payerLogo,
        name = credit.payerName,
        id = credit.payerId,
        theme = theme,
        modifier = Modifier.weight(weights.payer)
    )
    CreditReferenceCell(
        invoiceRef = credit.invoiceRef,
        txnRef = credit.txnRef,
        theme = theme,
        modifier = Modifier.weight(weights.ref)
    )
    CreditPurchasedCell(
        credit = credit,
        theme = theme,
        modifier = Modifier.weight(weights.purchased)
    )
    Text(
        text = credit.amount,
        color = theme.surface.contra.color,
        fontSize = 15.sp,
        fontWeight = FontWeight.Medium,
        modifier = Modifier.weight(weights.amount)
    )
    Box(modifier = Modifier.weight(weights.actions), contentAlignment = Alignment.CenterEnd) {
        MenuOption(
            colors = props.colors.menu,
            orientation = orientation,
            actions = actions,
        ) { action ->
        }
    }
}