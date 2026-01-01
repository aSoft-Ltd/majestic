package majestic.users.profile.tabs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import captain.Navigator
import cinematic.watchAsState
import majestic.ThemeColor
import majestic.users.profile.TabsLabels
import majestic.users.profile.tools.ProfileDestinationMapper

fun profileRoutes(endpoint: ProfileDestinationMapper, labels: TabsLabels) = listOf(
    labels.permissions.toPage(endpoint = endpoint.permissions),
    labels.contacts.toPage(endpoint = endpoint.contacts),
    labels.roles.toPage(endpoint = endpoint.roles),
    labels.security.toPage(endpoint = endpoint.security)
)

@Composable
internal fun ProfileTabs(
    labels: TabsLabels,
    themes: ThemeColor,
    navigator: Navigator,
    endpoint: ProfileDestinationMapper,
    modifier: Modifier,
) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(16.dp)
) {
    val url = navigator.route.watchAsState()
    profileRoutes(endpoint, labels).map { page ->
        Item(
            label = page.label,
            theme = themes,
            selected = page.isSelected(url.path),
            onClick = { navigator.navigate(page.path) }
        )
    }
}
