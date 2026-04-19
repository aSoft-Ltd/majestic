package majestic.shared.notifications

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.ScreenOrientation
import majestic.dialogs.flexible.FlexibleDialog
import majestic.shared.tools.modal.ModalColors
import majestic.shared.tools.modal.ModalHeader
import majestic.shared.tools.modal.modalHeaderStyle
import org.jetbrains.compose.resources.vectorResource

data class NotificationLabels(
    val title: String,
    val subtitle: String,
    val dateSent: String,
    val filters: NotificationFilterLabels,
    val actions: NotificationActionLabels
)

data class NotificationFilterLabels(
    val mine: String,
    val general: String,
    val all: String
)

data class NotificationActionLabels(
    val view: String,
    val reply: String,
    val pay: String,
    val markAsRead: String,
    val delete: String
)

data class ViewNotificationColors(
    val modal: ModalColors,
    val foreground: Color,
)

@Composable
fun ViewNotification(
    notification: NotificationItem? = null,
    onSetNotification: (NotificationItem?) -> Unit,
    labels: NotificationLabels,
    orientation: ScreenOrientation,
    colors: ViewNotificationColors,
    modifier: Modifier = Modifier
) {
    if (notification != null) FlexibleDialog(
        onDismiss = { onSetNotification(null) },
        modifier = modifier,
        bar = {
            ModalHeader(
                icon = vectorResource(notification.icon),
                title = notification.title,
                subtitle = notification.sender,
                onClose = { onSetNotification(null) },
                colors = colors.modal.copy(headerIconTint = Color(notification.color)),
                orientation = orientation,
                modifier = Modifier.modalHeaderStyle(colors.modal)
            )
        }
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 18.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = labels.dateSent,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = colors.foreground
                )
                Text(
                    text = notification.sentAt,
                    fontSize = 12.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = colors.foreground.copy(alpha = 0.5f)
                )
            }

            Text(
                text = notification.description,
                fontSize = 13.sp,
                lineHeight = 16.sp,
                color = colors.foreground
            )
        }
    }
}
