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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.ScreenOrientation
import majestic.dialogs.DialogColors
import majestic.icons.Res
import majestic.icons.ic_presentation
import majestic.shared.notices.list.NoticeItem
import majestic.shared.tools.modal.ModalColors
import majestic.shared.tools.modal.ModalHeader
import org.jetbrains.compose.resources.painterResource

data class NoticePopupColors(
    val dialog: DialogColors,
    val modal: ModalColors,
    val info: Color,
    val description: Color,
    val divider: Color,
)

@Composable
fun View(
    item: NoticeItem,
    colors: NoticePopupColors,
    orientation: ScreenOrientation,
    onDismiss: () -> Unit,
) = Column(
    modifier = Modifier.fillMaxWidth(),
    verticalArrangement = Arrangement.spacedBy(6.dp),
    horizontalAlignment = Alignment.CenterHorizontally
) {
    ModalHeader(
        title = item.title,
        subtitle = item.info.firstOrNull(),
        iconPainter = painterResource(Res.drawable.ic_presentation),
        onClose = onDismiss,
        colors = colors.modal,
        orientation = orientation,
        modifier = Modifier
            .clipToBounds()
            .fillMaxWidth()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
    )
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        HorizontalDivider(color = colors.divider, modifier = Modifier.fillMaxWidth())
        SubHeader(
            item,
            colors,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 10.dp)
        )
        HorizontalDivider(color = colors.divider, modifier = Modifier.fillMaxWidth())
        Text(
            text = item.description,
            color = colors.description,
            fontSize = 15.sp,
            lineHeight = 22.sp,
            modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
        )
    }
}