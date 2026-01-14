package majestic

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cinematic.watchAsState
import kotlinx.coroutines.launch
import symphony.LinearPaginationManager

@Composable
fun <T> Paginator(
    paginator: LinearPaginationManager<T>,
    modifier: Modifier = Modifier,
    colors: PaginatorColors = PaginatorColors(),
    icons: PaginatorIcons = PaginatorIcons()
) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.spacedBy(5.dp)
) {
    val current = paginator.current.watchAsState()
    val scope = rememberCoroutineScope()
    // start of list pages
    val noOfPages = 7 // for demo purposes, later this will be handled by the paginator manager
    val currentPage = current.data?.number
        ?: 0 // for demo purposes, later this will be handled by the paginator manager

    PaginatorItem(
        icon = icons.first,
        colors = colors,
        onClick = {/* TODO: Implement click for going to first page */ }
    )

    PaginatorItem(
        icon = icons.prev,
        colors = colors,
        onClick = { scope.launch { paginator.loadPage((currentPage - 1).coerceAtLeast(1)) } }
    )

    for (page in 1..noOfPages) PaginatorItem(
        active = page == currentPage,
        text = page.toString(),
        colors = colors,
        onClick = { scope.launch { paginator.loadPage(page) } }
    )

    PaginatorItem(
        icon = icons.next,
        colors = colors,
        onClick = {
            scope.launch { paginator.loadPage((currentPage + 1).coerceAtMost(noOfPages)) }
        }
    )
    PaginatorItem(
        icon = icons.last,
        colors = colors,
        onClick = {/* TODO: Implement click for going to last page */ }
    )
}

fun PaginatorColors.toDirectionColors(isHovered: Boolean) = when {
    isHovered -> direction.hovered.foreground
    else -> direction.inactive.foreground
}