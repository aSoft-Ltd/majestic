package majestic.payments.transaction.details

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.payments.labels.transaction.TransactionLabels
import majestic.payments.tools.InfoTag
import majestic.payments.tools.header.COLLAPSE_MILLIS
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
    collapsed: Boolean = false,
    onToggleCollapsed: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
) = when (orientation) {
    is Landscape -> DetailHeader(
        colors = colors,
        options = WalletDetailMenuAction.getMenus(labels.menu),
        details = details,
        collapsed = collapsed,
        onToggleCollapsed = onToggleCollapsed,
        modifier = modifier,
        icon = {
            val size by animateDpAsState(
                targetValue = if (collapsed) 48.dp else 100.dp,
                animationSpec = tween(COLLAPSE_MILLIS)
            )
            Image(
                modifier = Modifier.size(size).clip(CircleShape),
                painter = painterResource(image),
                contentScale = ContentScale.Crop,
                contentDescription = null,
            )
        }
    ) { HeaderInfo(title = title, country = country, tags = tags, orientation = Landscape, colors = colors.tooltip) }

    is Portrait -> DetailHeader(
        modifier = modifier,
        colors = colors,
        options = WalletDetailMenuAction.getMenus(labels.menu),
        icon = {
            Image(
                modifier = Modifier.size(50.dp).clip(CircleShape),
                painter = painterResource(image),
                contentScale = ContentScale.Crop,
                contentDescription = null,
            )
        }
    ) { HeaderInfo(title = title, tags = tags, orientation = Portrait, colors = colors.tooltip) }
}