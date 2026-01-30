package majestic.payments.wallet.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.payments.labels.wallet.WalletLabels
import majestic.payments.tools.header.DetailHeader
import majestic.payments.tools.header.DetailHeaderColors
import majestic.payments.tools.header.InfoEntryItem
import majestic.payments.wallet.tools.WalletDetailMenuAction
import org.jetbrains.compose.resources.painterResource
import tz.co.asoft.majestic_payments.generated.resources.Res
import tz.co.asoft.majestic_payments.generated.resources.ic_wallet_02_solid

@Composable
fun Header(
    title: String,
    labels: WalletLabels,
    colors: DetailHeaderColors,
    orientation: ScreenOrientation,
    modifier: Modifier = Modifier,
) {
    when (orientation) {
        is Landscape -> DetailHeader(
            title = title,
            colors = colors,
            orientation = Landscape,
            options = WalletDetailMenuAction.getMenus(labels.menu),
            details = InfoEntryItem.wallet(labels.infoEntry),
            modifier = modifier,
        ) {
            Icon(
                modifier = Modifier.size(100.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(colors.icon.background)
                    .padding(20.dp),
                painter = painterResource(Res.drawable.ic_wallet_02_solid),
                tint = colors.icon.foreground,
                contentDescription = null,
            )
        }

        is Portrait -> {}
    }
}
