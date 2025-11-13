package majestic.users.profile.tabs

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import captain.Navigator
import cinematic.watchAsState
import kollections.map
import majestic.ThemeColor
import majestic.users.profile.TabsLabels
import majestic.users.profile.tabs.toPage
import majestic.users.profile.tools.ProfileDestinationMapper


@Composable
internal fun Tabs(
    labels: TabsLabels,
    themes: ThemeColor,
    navigator: Navigator,
    endpoint: ProfileDestinationMapper,
    modifier: Modifier = Modifier
) {
    val url = navigator.route.watchAsState()
    val pages = enrolledPages(endpoint, labels).map {
        TabItem(label = it.label, path = it.path)
    }
    Tabs(
        modifier = modifier,
        current = url.path,
        pages = pages,
        themes = themes,
        navigator = navigator
    )
}

private fun enrolledPages(endpoint: ProfileDestinationMapper, labels: TabsLabels) = listOf(
    labels.permissions.toPage(endpoint = endpoint.permissions()),
    labels.contacts.toPage(endpoint = endpoint.contacts()),
    labels.roles.toPage(endpoint = endpoint.roles()),
    labels.security.toPage(endpoint = endpoint.security()),
)
