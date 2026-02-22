package majestic.payments.wallet.table

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.ColorPair
import majestic.dropdown.Dropdown
import majestic.icons.Res
import majestic.icons.ic_more_vertical
import majestic.icons.ic_wallet_02_solid
import majestic.payments.labels.wallet.WalletLabels
import majestic.payments.tools.table.TableColors
import majestic.payments.wallet.tools.Avatar
import majestic.payments.wallet.tools.AvatarOverflow
import majestic.payments.wallet.tools.WalletMenuAction
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.vectorResource

@Composable
fun WalletListRow(
    labels: WalletLabels,
    colors: TableColors,
    detail: PaymentWallet,
    modifier: Modifier = Modifier
) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(12.dp)
) {
    Icon(
        modifier = Modifier.size(42.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(colors.icon.copy(0.1f))
            .padding(8.dp),
        painter = painterResource(Res.drawable.ic_wallet_02_solid),
        tint = colors.icon,
        contentDescription = null,
    )
    Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(5.dp)) {
        Text(
            text = detail.name,
            color = colors.foreground,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = 14.sp,
            lineHeight = 1.sp
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Avatar(
                color = colors.background,
                images = detail.accounts,
                size = 20.dp,
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier.width(44.dp)
            )
            Text(
                text = "•",
                color = colors.foreground.copy(0.6f),
                lineHeight = 1.sp
            )
            Avatar(
                color = colors.background,
                images = detail.transactions,
                size = 18.dp,
                maxVisible = 3,
                overflow = AvatarOverflow(
                    size = 18.dp,
                    fontSize = 5.sp,
                    shape = CircleShape,
                    color = ColorPair(
                        background = colors.background,
                        foreground = colors.foreground
                    )
                ),
                modifier = Modifier.width(56.dp)
            )
            Text(
                text = "•",
                color = colors.foreground.copy(0.6f),
                lineHeight = 1.sp
            )
            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = "TZS ",
                    color = colors.foreground.copy(0.5f),
                    fontSize = 9.sp,
                    lineHeight = 1.sp
                )
                Text(
                    text = "${detail.amount}/=",
                    color = colors.foreground,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 12.sp,
                    lineHeight = 1.sp
                )
            }
        }
    }

    Dropdown(
        items = WalletMenuAction.getMenus(labels.menu).toDropdownItems(),
        onAction = { /* TODO */ },
        colors = colors.dropdown,
        icon = vectorResource(Res.drawable.ic_more_vertical),
        isListItem = true
    )
}