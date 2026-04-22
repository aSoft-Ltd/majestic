package majestic.shared.notifications.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.Landscape
import composex.screen.orientation.ScreenOrientation
import majestic.dropdown.Dropdown
import majestic.icons.Res
import majestic.icons.ic_more_horizontal
import majestic.shared.notifications.NotificationActionLabels
import majestic.shared.notifications.NotificationItem
import majestic.shared.tools.dropdown.toDropdownItems
import majestic.shared.tools.menu.OptionMenu
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.vectorResource

@Composable
internal fun NotificationCard(
    colors: NotificationsPageContentColors,
    notification: NotificationItem,
    orientation: ScreenOrientation,
    labels: NotificationActionLabels,
    onAction: (NotificationHandler) -> Unit,
    modifier: Modifier = Modifier
) {
    val accent = Color(notification.color)
    val actions = remember(labels) {
        listOf(
            OptionMenu(NotificationAction.View.getLabel(labels), NotificationAction.View),
            OptionMenu(NotificationAction.Reply.getLabel(labels), NotificationAction.Reply),
            OptionMenu(NotificationAction.Pay.getLabel(labels), NotificationAction.Pay),
            OptionMenu(NotificationAction.MarkAsRead.getLabel(labels), NotificationAction.MarkAsRead),
            OptionMenu(
                NotificationAction.Delete.getLabel(labels),
                NotificationAction.Delete,
                isDestructive = true
            )
        )
    }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Box(
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
                .background(accent.copy(alpha = 0.14f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(notification.icon),
                contentDescription = null,
                tint = accent,
                modifier = Modifier.size(22.dp)
            )
        }

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = notification.title,
                color = colors.foreground,
                fontSize = 16.sp,
                lineHeight = 1.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = notification.description,
                color = colors.foreground.copy(.62f),
                fontSize = 14.sp,
                lineHeight = 20.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = notification.date,
                color = colors.foreground.copy(.38f),
                fontSize = 11.sp,
                lineHeight = 20.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (!notification.isRead)
                Box(
                    modifier = Modifier
                        .size(5.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF37D67A))
                )

            Dropdown(
                items = actions.toDropdownItems(),
                onAction = { action -> action.handle(notification, onAction) },
                colors = colors.dropdown,
                icon = vectorResource(Res.drawable.ic_more_horizontal),
                rotationTarget = if (orientation == Landscape) 0f else 90f,
                isListItem = true
            )
        }
    }
}