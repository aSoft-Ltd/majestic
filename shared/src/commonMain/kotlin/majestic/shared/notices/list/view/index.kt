package majestic.shared.notices.list.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.ScreenOrientation
import majestic.dialogs.DialogColors
import majestic.icons.Res
import majestic.icons.ic_presentation
import majestic.shared.notices.list.NoticeItem
import majestic.shared.tools.modal.ModalColors
import majestic.shared.tools.modal.modalHeaderStyle
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
) = Column(modifier = Modifier.fillMaxWidth()) {
    Header(
        title = item.title,
        subtitle = item.info.firstOrNull(),
        iconPainter = painterResource(Res.drawable.ic_presentation),
        onClose = onDismiss,
        colors = colors.modal,
        orientation = orientation,
        modifier = Modifier.modalHeaderStyle(colors = colors.modal)
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        HorizontalDivider(color = colors.divider, modifier = Modifier.fillMaxWidth())
        SubHeader(item, colors)
        HorizontalDivider(color = colors.divider, modifier = Modifier.fillMaxWidth())
        Text(
            text = item.description,
            color = colors.description,
            fontSize = 15.sp,
            lineHeight = 22.sp
        )
    }
}

