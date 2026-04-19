package majestic.shared.notifications.page

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.shared.notifications.NotificationFilterLabels

@Composable
internal fun NotificationLandscapeHeader(
    colors: NotificationsPageContentColors,
    title: String,
    subtitle: String,
    labels: NotificationFilterLabels,
    filter: NotificationFilter,
    onFilterChange: (NotificationFilter) -> Unit,
    modifier: Modifier = Modifier
) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween
) {
    Column(modifier = Modifier.padding(end = 16.dp)) {
        Text(
            text = title,
            color = colors.foreground,
            fontSize = 16.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = subtitle,
            color = colors.foreground.copy(.5f),
            fontSize = 13.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }

    NotificationFilters(
        colors = colors,
        filter = filter,
        labels = labels,
        onFilterChange = onFilterChange,
        modifier = Modifier.horizontalScroll(rememberScrollState())
    )
}