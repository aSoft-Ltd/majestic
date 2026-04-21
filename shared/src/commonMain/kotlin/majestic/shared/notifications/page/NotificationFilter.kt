package majestic.shared.notifications.page

import majestic.shared.notifications.NotificationFilterLabels
import majestic.shared.notifications.NotificationItem

enum class NotificationFilter {
    Mine,
    General,
    All;

    fun getLabel(labels: NotificationFilterLabels): String = when (this) {
        Mine -> labels.mine
        General -> labels.general
        All -> labels.all
    }

    fun matches(notification: NotificationItem): Boolean = when (this) {
        All -> true
        else -> name.equals(notification.type, ignoreCase = true)
    }
}