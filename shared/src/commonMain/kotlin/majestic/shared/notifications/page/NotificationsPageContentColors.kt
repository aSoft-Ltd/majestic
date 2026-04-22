package majestic.shared.notifications.page

import androidx.compose.ui.graphics.Color
import majestic.dropdown.DropdownColors
import majestic.shared.notifications.ViewNotificationColors
import majestic.shared.tools.table.TableColors

data class NotificationsPageContentColors(
    val viewNotificationColors: ViewNotificationColors,
    val modalBackground: Color,
    val foreground: Color,
    val tableColors: TableColors,
    val notificationItemBackground: Color,
    val dropdown: DropdownColors
)