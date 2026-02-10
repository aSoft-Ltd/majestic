package majestic.shared.credit.tools

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import composex.screen.orientation.ScreenOrientation
import majestic.shared.credit.CreditUsage
import majestic.shared.menu.MenuOption
import majestic.shared.menu.OptionMenu
import majestic.shared.tools.PhotoBadge

@Composable
fun CreditPortraitRow(
    credit: CreditUsage,
    colors: CreditTableColors,
    orientation: ScreenOrientation,
    modifier: Modifier = Modifier,
    actions: List<OptionMenu<CreditAction>>,
    onAction: (CreditAction) -> Unit
) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(12.dp)
) {
    PhotoBadge(
        recipient = credit.recipientLogo,
        payer = credit.payerLogo,
        size = 25.dp
    )
    CreditItem(
        modifier = Modifier.weight(1f),
        name = credit.payerName,
        schoolName = credit.recipientName,
        badgeText = credit.itemName,
        badgeColor = credit.itemColor,
        amount = credit.amount,
        avatar = credit.logo,
        colors = colors
    )

    MenuOption(
        colors = colors.menu,
        orientation = orientation,
        actions = actions,
    ) { action ->
        onAction(action)
    }
}