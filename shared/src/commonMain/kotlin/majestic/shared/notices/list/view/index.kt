package majestic.shared.notices.list.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.Landscape
import composex.screen.orientation.ScreenOrientation
import majestic.dialogs.DialogColors
import majestic.icons.Res
import majestic.icons.ic_presentation
import majestic.shared.notices.list.NoticeItem
import org.jetbrains.compose.resources.vectorResource

data class NoticePopupColors(
    val dialog: DialogColors,
    val header: HeaderColors,
    val info: Color,
    val description: Color,
    val divider: Color,
)

@Composable
fun View(
    item: NoticeItem,
    connector: String,
    colors: NoticePopupColors,
    orientation: ScreenOrientation,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) = Column(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(6.dp),
    horizontalAlignment = Alignment.CenterHorizontally
) {
    val outerPadding = if (orientation is Landscape) 24.dp else 12.dp
    val innerPadding = if (orientation is Landscape) 12.dp else 6.dp
    Header(
        title = item.title,
        subtitle = item.info.firstOrNull(),
        icon = vectorResource(Res.drawable.ic_presentation),
        onClose = onDismiss,
        colors = colors.header,
        orientation = orientation,
        modifier = Modifier
            .clipToBounds()
            .fillMaxWidth()
            .padding(
                start = outerPadding,
                top = outerPadding,
                end = outerPadding,
                bottom = innerPadding
            )
    )
    Column(modifier = Modifier.fillMaxWidth()) {
        HorizontalDivider(color = colors.divider, modifier = Modifier.fillMaxWidth())
        SubHeader(
            item,
            colors = colors,
            connector = connector,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = outerPadding, vertical = innerPadding),
            orientation = orientation
        )
        HorizontalDivider(color = colors.divider, modifier = Modifier.fillMaxWidth())
        Text(
            text = item.description,
            color = colors.description,
            fontSize = 15.sp,
            lineHeight = 20.sp,
            maxLines = 20,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Justify,
            modifier = Modifier.fillMaxWidth().padding(outerPadding)
        )
    }
}