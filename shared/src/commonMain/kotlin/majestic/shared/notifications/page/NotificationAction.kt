package majestic.shared.notifications.page

import majestic.shared.notifications.NotificationActionLabels
import majestic.shared.notifications.NotificationItem

enum class NotificationAction {
    View,
    Reply,
    Pay,
    MarkAsRead,
    Delete;

    fun getLabel(labels: NotificationActionLabels): String = when (this) {
        View -> labels.view
        Reply -> labels.reply
        Pay -> labels.pay
        MarkAsRead -> labels.markAsRead
        Delete -> labels.delete
    }

    fun handle(
        notification: NotificationItem,
        onClickOption: (NotificationHandler) -> Unit
    ) {
        onClickOption(NotificationHandler(this, notification))
    }
}