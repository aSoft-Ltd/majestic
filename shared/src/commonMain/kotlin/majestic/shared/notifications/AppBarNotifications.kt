package majestic.shared.notifications

import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import captain.Navigator
import composex.screen.orientation.Landscape
import composex.screen.orientation.ScreenOrientation
import majestic.shared.menu.PanelMenu
import majestic.shared.tools.rememberHoverBackground
import majestic.shared.tools.separator
import majestic.tooling.onClick

data class AppBarNotificationsColors(
    val foreground: Color,
    val appBarBackground: Color,
    val panelBackground: Color,
)

data class AppBarNotificationsLabels(
    val title: String,
    val markAllAsRead: String,
    val viewAll: String,
)

@Composable
fun AppBarNotifications(
    notifications: List<AppBarNotification>,
    colors: AppBarNotificationsColors,
    labels: AppBarNotificationsLabels,
    navigator: Navigator,
    viewAllPath: String,
    orientation: ScreenOrientation,
    modifier: Modifier = Modifier,
) {
    PanelMenu(
        modifier = modifier,
        trigger = { _, onToggle ->
            val (background, interaction) = rememberHoverBackground(
                background = colors.appBarBackground,
                foreground = colors.foreground,
                targetAlpha = 1f,
                startingAlpha = 0.5f
            )

            AppBarNotificationsIcon(
                count = 32,
                colors = colors,
                interaction = interaction,
                modifier = Modifier
                    .size(40.dp)
                    .background(color = background, shape = CircleShape)
                    .hoverable(interaction)
                    .pointerHoverIcon(PointerIcon.Hand)
                    .onClick { if (orientation == Landscape) onToggle() else navigator.navigate(viewAllPath) }
            )
        },
        content = { _ ->
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(43.dp)
                    .separator(colors.foreground.copy(0.1f))
                    .padding(horizontal = 13.dp, vertical = 7.dp),
            ) {
                Text(text = labels.title, fontWeight = FontWeight.Medium, fontSize = 11.sp, color = colors.foreground)
                Text(
                    text = labels.markAllAsRead,
                    fontSize = 11.sp,
                    color = colors.foreground.copy(0.5f),
                    modifier = Modifier.pointerHoverIcon(PointerIcon.Hand)
                )
            }

            Column(modifier = Modifier.height(363.dp).verticalScroll(rememberScrollState())) {
                notifications.forEach { notification ->
                    AppBarNotificationItem(
                        notification = notification,
                        colors = colors,
                        modifier = Modifier.appBarNotificationItem(colors = colors, onClick = {})
                    )
                }
            }

            Text(
                text = labels.viewAll,
                fontSize = 11.sp,
                color = colors.foreground.copy(0.7f),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 9.dp, horizontal = 7.dp)
                    .wrapContentWidth(Alignment.CenterHorizontally)
                    .pointerHoverIcon(PointerIcon.Hand)
                    .onClick { navigator.navigate(viewAllPath) }
            )
        }
    )
}