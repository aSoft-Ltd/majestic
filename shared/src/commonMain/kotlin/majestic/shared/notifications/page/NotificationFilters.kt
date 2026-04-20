package majestic.shared.notifications.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import majestic.button.Button
import majestic.button.appearence.translucentButton
import majestic.button.basic.BasicButtonContent
import majestic.shared.notifications.NotificationFilterLabels

@Composable
fun NotificationFilters(
    colors: NotificationsPageContentColors,
    filter: NotificationFilter,
    labels: NotificationFilterLabels,
    onFilterChange: (NotificationFilter) -> Unit,
    modifier: Modifier = Modifier
) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.spacedBy(8.dp),
    verticalAlignment = Alignment.CenterVertically
) {
    NotificationFilter.entries.forEach { item ->
        val selected = item == filter
        Button(
            modifier = Modifier.translucentButton(
                color = colors.foreground,
                alpha = if (selected) 0.15f else 0.06f,
                onClick = { onFilterChange(item) }
            ).widthIn(min = 72.dp).padding(horizontal = 12.dp, vertical = 4.dp),
        ) { buttonColors ->
            BasicButtonContent(
                text = item.getLabel(labels),
                colors = buttonColors
            )
        }
    }
}