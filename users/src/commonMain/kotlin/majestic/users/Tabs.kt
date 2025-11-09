//package majestic.users
//
//import academia.admission.bulk.EnrollDestinations
//import academia.admission.enroll.detailed.TabLabels
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import captain.Navigator
//import cinematic.watchAsState
//import kollections.map
//import majestic.ThemeColor
//import tz.co.asoft.academia.tools.tabs.TabItem
//import tz.co.asoft.academia.tools.tabs.Tabs
//import tz.co.asoft.academia.tools.util.toPage
//
//
//@Composable
//internal fun Tabs(
//    labels: TabLabels,
//    themes: ThemeColor,
//    navigator: Navigator,
//    endpoint: EnrollDestinations,
//    modifier: Modifier = Modifier
//) {
//    val url = navigator.route.watchAsState()
//    val pages = enrolledPages(endpoint, labels).map {
//        TabItem(label = it.label, path = it.path)
//    }
//    Tabs(
//        modifier = modifier,
//        current = url.path,
//        pages = pages,
//        themes = themes,
//        navigator = navigator
//    )
//}
//
//private fun enrolledPages(endpoint: EnrollDestinations, labels: TabLabels) = listOf(
//    labels.criteria.toPage(endpoint = endpoint.index()),
//    labels.candidates.toPage(endpoint = endpoint.candidates()),
//)
