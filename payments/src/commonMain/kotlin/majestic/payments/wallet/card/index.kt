package majestic.payments.wallet.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.Landscape
import majestic.dropdown.Dropdown
import majestic.dropdown.DropdownColors
import majestic.payments.labels.wallet.WalletLabels
import majestic.payments.wallet.tools.Avatar
import majestic.payments.wallet.tools.WalletMenuAction
import majestic.shared.tools.dropdown.toDropdownItems
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.vectorResource
import tz.co.asoft.majestic_payments.generated.resources.Res
import tz.co.asoft.majestic_payments.generated.resources.ic_more_horizontal
import tz.co.asoft.majestic_payments.generated.resources.ic_wallet_02_solid

data class WalletCardColors(
    val text: Color,
    val icon: Color,
    val background: Color,
    val dropdown: DropdownColors
)

@Composable
fun WalletCard(
    labels: WalletLabels,
    title: String,
    amount: String,
    accounts: List<DrawableResource>,
    transactions: List<DrawableResource>,
    colors: WalletCardColors,
    modifier: Modifier = Modifier,
) = Column(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(20.dp),
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Icon(
                modifier = Modifier.size(46.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(colors.icon.copy(0.1f))
                    .padding(12.dp),
                painter = painterResource(Res.drawable.ic_wallet_02_solid),
                tint = colors.icon,
                contentDescription = null,
            )
            Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                Text(
                    text = title,
                    color = colors.text,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    lineHeight = 1.sp
                )
                Avatar(
                    color = colors.background,
                    images = accounts,
                    size = 24.dp,
                    shape = RoundedCornerShape(5.dp)
                )
            }
        }
        Dropdown(
            items = WalletMenuAction.getMenus(labels.menu).toDropdownItems(),
            onAction = { /* TODO */ },
            colors = colors.dropdown,
            icon = vectorResource(Res.drawable.ic_more_horizontal),
            isListItem = true
        )
    }
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Avatar(
            images = transactions.slice(indices = 0..3),
            color = colors.background,
            overlapPercent = 0.4f,
            border = 3.dp,
            size = 28.dp,
            maxVisible = 4,
            modifier = Modifier.width(90.dp)
        )
        Text(
            text = "${transactions.size} ${labels.transactions}",
            color = colors.text.copy(0.7f),
            lineHeight = 1.sp,
            fontSize = 12.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(space = 5.dp, alignment = Alignment.End)
    ) {
        Text(
            text = "TZS",
            color = colors.text.copy(0.3f),
            lineHeight = 1.sp,
            fontSize = 12.sp
        )
        Text(
            text = "$amount/=",
            color = colors.text,
            lineHeight = 1.sp,
            fontSize = 16.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}