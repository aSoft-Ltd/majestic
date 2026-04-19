package majestic.shared.notifications.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import captain.Navigator
import majestic.button.appearence.headerIconButton
import majestic.button.basic.HeaderIconButton
import majestic.icons.Res
import majestic.icons.ic_arrow_left
import org.jetbrains.compose.resources.vectorResource

@Composable
internal fun NotificationPortraitTopBar(
    colors: NotificationsPageContentColors,
    navigator: Navigator,
    title: String,
    modifier: Modifier = Modifier
) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(10.dp)
) {
    HeaderIconButton(
        icon = vectorResource(Res.drawable.ic_arrow_left),
        modifier = Modifier.headerIconButton(
            color = colors.foreground,
            alpha = 0f,
            onClick = { navigator.go(-1) }
        )
    )

    Text(
        text = title,
        color = colors.foreground,
        fontSize = 15.sp,
        fontWeight = FontWeight.SemiBold,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}