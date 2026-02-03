package majestic.payments.transaction.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.payments.labels.transaction.TransactionLabels
import majestic.payments.tools.InfoTag
import majestic.payments.tools.header.DetailHeader
import majestic.payments.tools.header.DetailHeaderColors
import majestic.payments.tools.header.InfoEntryItem
import majestic.payments.wallet.tools.WalletDetailMenuAction
import nation.Country
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun Header(
    title: String,
    labels: TransactionLabels,
    colors: DetailHeaderColors,
    image: DrawableResource,
    orientation: ScreenOrientation,
    country: Country,
    tags: List<InfoTag>,
    details: List<InfoEntryItem>,
    modifier: Modifier = Modifier,
) = when (orientation) {
    is Landscape -> DetailHeader(
        colors = colors,
        orientation = Landscape,
        options = WalletDetailMenuAction.getMenus(labels.menu),
        details = details,
        modifier = modifier,
        icon = {
            Image(
                modifier = Modifier.size(100.dp).clip(CircleShape),
                painter = painterResource(image),
                contentScale = ContentScale.Crop,
                contentDescription = null,
            )
        }
    ) {
        HeaderInfo(
            title = title,
            country = country,
            tags = tags,
            orientation = Landscape,
            colors = colors.tooltip
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
        HeaderInfo(
            title = title,
            tags = tags,
            orientation = Portrait,
            colors = colors.tooltip
        )
    }
}
