package majestic.payments.transaction.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.payments.labels.transaction.TransactionLabels
import majestic.payments.tools.header.DetailHeader
import majestic.payments.tools.header.DetailHeaderColors
import majestic.payments.tools.header.InfoEntryItem
import majestic.payments.wallet.tools.WalletDetailMenuAction
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

private fun Modifier.infoBox(background: Color) = this
    .clip(RoundedCornerShape(2.dp))
    .background(background)
    .padding(horizontal = 4.dp, vertical = 2.dp)

@Composable
fun Header(
    title: String,
    labels: TransactionLabels,
    colors: DetailHeaderColors,
    image: DrawableResource,
    orientation: ScreenOrientation,
    details: List<InfoEntryItem>,
    modifier: Modifier = Modifier,
) = when (orientation) {
    is Landscape -> DetailHeader(
        title = title,
        colors = colors,
        orientation = Landscape,
        options = WalletDetailMenuAction.getMenus(labels.menu),
        details = details,
        modifier = modifier,
    ) {
        Image(
            modifier = Modifier.size(100.dp).clip(CircleShape),
            painter = painterResource(image),
            contentScale = ContentScale.Crop,
            contentDescription = null,
        )
    }

    is Portrait -> DetailHeader(
        modifier = modifier,
        colors = colors.menu,
        orientation = Portrait,
        options = WalletDetailMenuAction.getMenus(labels.menu),
        icon = {
            Image(
                modifier = Modifier.size(50.dp).clip(CircleShape),
                painter = painterResource(image),
                contentScale = ContentScale.Crop,
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
        Row(
            modifier = Modifier.horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
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
