package majestic.shared.notifications.page

import majestic.shared.notifications.NotificationItem

data class NotificationHandler(
    val action: NotificationAction,
    val notification: NotificationItem,
)