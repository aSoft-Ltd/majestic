package majestic.shared.notifications

import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.shared.tools.rememberHoverBackground
import majestic.tooling.onClick
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.vectorResource

data class AppBarNotification(
    val icon: DrawableResource,
    val color: Long,
    val title: String,
    val date: String,
    val description: String,
    val sender: String,
    val isRead: Boolean,
)

@Composable
fun Modifier.appBarNotificationItem(colors: AppBarNotificationsColors, onClick: () -> Unit): Modifier {
    val (background, interaction) = rememberHoverBackground(colors.panelBackground, colors.foreground, 0.2f)

    return this
        .onClick { onClick() }
        .background(background)
        .hoverable(interaction)
        .pointerHoverIcon(PointerIcon.Hand)
        .padding(horizontal = 13.dp, vertical = 7.dp)
}

@Composable
fun AppBarNotificationItem(
    notification: AppBarNotification,
    colors: AppBarNotificationsColors,
    modifier: Modifier = Modifier
) {
    val (icon, color, title, date, description, sender, isRead) = notification
    Row(modifier = modifier, verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.spacedBy(7.dp)) {
        Icon(
            imageVector = vectorResource(icon),
            contentDescription = title,
            tint = Color(color),
            modifier = Modifier
                .padding(top = 3.5.dp)
                .clip(CircleShape)
                .background(Color(color).copy(alpha = 0.1f))
                .size(23.dp)
                .wrapContentSize(Alignment.Center)
                .size(15.dp)
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(7.dp)) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Medium,
                    fontSize = 11.sp,
                    color = colors.foreground,
                    lineHeight = 11.sp
                )
                Text(
                    text = date,
                    fontSize = 11.sp,
                    color = colors.foreground.copy(alpha = 0.5f),
                    lineHeight = 11.sp
                )
            }
            Text(
                text = description,
                fontSize = 11.sp,
                color = colors.foreground.copy(alpha = 0.7f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                lineHeight = 11.sp
            )
            Text(
                text = sender,
                fontSize = 11.sp,
                color = colors.foreground.copy(alpha = 0.5f),
                lineHeight = 11.sp
            )
        }

        StatusBeacon(modifier = Modifier.align(Alignment.CenterVertically).alpha(if (isRead) 0f else 1f))
    }
}