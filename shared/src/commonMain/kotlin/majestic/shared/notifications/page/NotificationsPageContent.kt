package majestic.shared.notifications.page

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
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
import captain.Navigator
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
fun NotificationsPageContent(
    labels: NotificationLabels,
    orientation: ScreenOrientation,
    colors: NotificationsPageContentColors,
    navigator: Navigator,
    notifications: List<NotificationItem>
) {
    var filter by remember { mutableStateOf(NotificationFilter.Mine) }
    var notificationState by remember { mutableStateOf<NotificationItem?>(null) }

    ViewNotification(
        notification = notificationState,
        onSetNotification = { notification -> notificationState = notification },
        labels = labels,
        orientation = orientation,
        colors = colors.viewNotificationColors,
        modifier = Modifier.dialog(
            orientation = orientation,
            background = colors.modalBackground,
            size = Size(width = 604.dp, height = 230.dp)
        )
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(if (orientation == Landscape) 8.dp else 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (orientation) {
            is Landscape -> NotificationLandscapeHeader(
                colors = colors,
                title = labels.title,
                subtitle = labels.subtitle,
                filter = filter,
                labels = labels.filters,
                onFilterChange = { filter = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .separator(colors.foreground.copy(.05f))
                    .padding(horizontal = 10.dp)
            )

            is Portrait -> Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colors.tableColors.header)
                    .separator(colors.tableColors.headerBorder)
            ) {
                NotificationPortraitTopBar(
                    colors = colors,
                    navigator = navigator,
                    title = labels.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp)
                        .padding(horizontal = 10.dp, vertical = 10.dp)
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(if (orientation is Landscape) 0.65f else 1f)
                .background(if (orientation is Portrait) colors.tableColors.body else Color.Transparent),
            verticalArrangement = Arrangement.spacedBy(if (orientation is Portrait) 0.dp else 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (orientation is Portrait) {
                item {
                    NotificationFilters(
                        colors = colors,
                        filter = filter,
                        labels = labels.filters,
                        onFilterChange = { filter = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState())
                            .padding(horizontal = 10.dp, vertical = 10.dp)
                    )
                }
            }

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
                val onNotificationAction: (NotificationActionClick) -> Unit = { action ->
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
                        .padding(horizontal = if (orientation is Landscape) 18.dp else 0.dp)
                        .then(
                            if (orientation is Portrait) {
                                Modifier
                                    .background(colors.tableColors.body)
                                    .separator(colors.tableColors.bodyBorder)
                            }
                            else {
                                Modifier
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
}