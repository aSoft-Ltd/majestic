package majestic

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardDoubleArrowLeft
import androidx.compose.material.icons.filled.KeyboardDoubleArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    PaginatorItem(colors = colors) {
        val color = when {
            it.isHovered -> colors.hovered.foreground
            else -> colors.inactive.foreground.copy(0.5f)
        }
        if (icons.firstPage != null) icons.firstPage.invoke() else Icon(
            imageVector = Icons.Default.KeyboardDoubleArrowLeft,
            tint = color,
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
    }
    PaginatorItem(colors = colors) {
        val color = when {
            it.isHovered -> colors.hovered.foreground
            else -> colors.inactive.foreground.copy(0.7f)
        }
        if (icons.previousPage != null) icons.previousPage.invoke() else Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
            tint = color,
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
    }
    // start of list pages
    val noOfPages = 7 // for demo purposes, later this will be handled by the paginator manager
    val currentPage = 4 // for demo purposes, later this will be handled by the paginator manager
    for (page in 1..noOfPages) PaginatorItem(active = page == currentPage, colors = colors) { state ->
        val color = when {
            state.isActive -> colors.active.foreground
            state.isHovered -> colors.hovered.foreground
            else -> colors.inactive.foreground.copy(0.7f)
        }
        Box(modifier = Modifier.size(24.dp), contentAlignment = Alignment.Center) {
            Text(
                page.toString(),
                color = color,
                lineHeight = 0.1.sp,
                fontWeight = if (state.isActive) FontWeight.Bold else null
            )
        }
    }
    // end of list pages
    PaginatorItem(colors = colors) {
        val color = when {
            it.isHovered -> colors.hovered.foreground
            else -> colors.inactive.foreground.copy(0.7f)
        }
        if (icons.nextPage != null) icons.nextPage.invoke() else Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            tint = color,
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
    }
    PaginatorItem(colors = colors) {
        val color = when {
            it.isHovered -> colors.hovered.foreground
            else -> colors.inactive.foreground.copy(0.5f)
        }
        if (icons.lastPage != null) icons.lastPage.invoke() else Icon(
            imageVector = Icons.Default.KeyboardDoubleArrowRight,
            tint = color,
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
    }
}
