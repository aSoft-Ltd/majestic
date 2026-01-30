package majestic.payments.wallet.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

private fun Modifier.infoBox(background: Color) = this
    .clip(RoundedCornerShape(2.dp))
    .background(background)
    .padding(horizontal = 4.dp, vertical = 2.dp)

@Composable
fun Header(
    title: String,
    labels: WalletLabels,
    colors: DetailHeaderColors,
    orientation: ScreenOrientation,
    modifier: Modifier = Modifier,
) {
    val details = InfoEntryItem.wallet(labels.infoEntry)

    when (orientation) {
        is Landscape -> DetailHeader(
            title = title,
            colors = colors,
            orientation = Landscape,
            options = WalletDetailMenuAction.getMenus(labels.menu),
            details = details,
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

        is Portrait -> DetailHeader(
            modifier = modifier,
            colors = colors.menu,
            orientation = Portrait,
            options = WalletDetailMenuAction.getMenus(labels.menu),
            icon = {
                Icon(
                    modifier = Modifier.size(40.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .background(colors.icon.background)
                        .padding(8.dp),
                    painter = painterResource(Res.drawable.ic_wallet_02_solid),
                    tint = colors.icon.foreground,
                    contentDescription = null,
                )
            }
        ) {
            Text(
                modifier = Modifier.padding(bottom = 4.dp),
                text = title,
                color = colors.foreground,
                fontSize = 14.sp,
                lineHeight = 1.sp
            )
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                val account = details[1]
                val transaction = details[2]
                Text(
                    modifier = Modifier.infoBox(colors.foreground.copy(0.1f)),
                    text = "${account.title} ${account.description}",
                    color = colors.foreground.copy(0.5f),
                    fontSize = 10.sp,
                    lineHeight = 1.sp
                )
                Text(
                    modifier = Modifier.infoBox(colors.foreground.copy(0.1f)),
                    text = "${transaction.title} ${transaction.description}",
                    color = colors.foreground.copy(0.5f),
                    fontSize = 10.sp,
                    lineHeight = 1.sp
                )
            }
        }
    }
}
