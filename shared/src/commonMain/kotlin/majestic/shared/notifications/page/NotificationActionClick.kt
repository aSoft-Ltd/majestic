package majestic.shared.notifications.page

import majestic.shared.notifications.NotificationItem

data class NotificationActionClick(
    val action: NotificationAction,
    val notification: NotificationItem,
)