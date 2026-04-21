package majestic.shared.notifications.page

import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.shared.notifications.NotificationItem
import majestic.shared.notifications.NotificationLabels
import majestic.shared.notifications.ViewNotification
import majestic.shared.tools.modal.Size
import majestic.shared.tools.modal.dialog
import majestic.shared.tools.rememberHoverBackground
import majestic.tooling.onClick
import majestic.tooling.separator

private fun List<NotificationItem>.filteredBy(filter: NotificationFilter): List<NotificationItem> =
    filter { filter.matches(it) }

@Composable
fun NotificationsList(
    labels: NotificationLabels,
    orientation: ScreenOrientation,
    colors: NotificationsPageContentColors,
    notifications: List<NotificationItem>,
    filter: NotificationFilter,
) {
    var notificationState by remember { mutableStateOf<NotificationItem?>(null) }

    ViewNotification(
        notification = notificationState,
        onSetNotification = { notification -> notificationState = notification },
        labels = labels,
        orientation = orientation,
        colors = colors.viewNotificationColors,
        onSubmit = { notificationState = null },
        onCancel = { notificationState = null },
        modifier = Modifier.dialog(
            orientation = orientation,
            background = colors.modalBackground,
            size = Size(width = 604.dp, height = 304.dp)
        )
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(if (orientation is Landscape) 0.65f else 1f)
            .background(if (orientation is Portrait) colors.tableColors.body else Color.Transparent),
        verticalArrangement = Arrangement.spacedBy(if (orientation is Portrait) 0.dp else 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (orientation is Landscape) {
            item {
                Spacer(modifier = Modifier.height(2.dp))
            }
        }

        items(notifications.filteredBy(filter)) { notification ->
            val (background, interaction) = rememberHoverBackground(
                colors.notificationItemBackground,
                colors.foreground
            )
            val onNotificationAction: (NotificationHandler) -> Unit = { action ->
                when (action.action) {
                    NotificationAction.View -> notificationState = action.notification
                    NotificationAction.Reply -> Unit
                    NotificationAction.Pay -> Unit
                    NotificationAction.MarkAsRead -> Unit
                    NotificationAction.Delete -> Unit
                }
            }
            NotificationCard(
                colors = colors,
                notification = notification,
                orientation = orientation,
                labels = labels.actions,
                onAction = onNotificationAction,
                modifier = Modifier
                    .fillMaxWidth()
                    .then(
                        if (orientation is Portrait) {
                            Modifier.separator(colors.tableColors.bodyBorder)
                        }
                        else {
                            Modifier
                                .padding(horizontal = 18.dp)
                                .clip(RoundedCornerShape(14.dp))
                                .background(background)
                                .hoverable(interaction)
                        }
                    )
                    .padding(horizontal = 14.dp, vertical = 16.dp)
                    .onClick { NotificationAction.View.handle(notification, onNotificationAction) }
            )
        }

        if (orientation is Landscape) {
            item {
                Spacer(modifier = Modifier.height(2.dp))
            }
        }
    }
}