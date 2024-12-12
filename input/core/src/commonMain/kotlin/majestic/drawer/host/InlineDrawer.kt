package majestic.drawer.host

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import majestic.drawer.Drawer
import majestic.drawer.DrawerPosition
import majestic.drawer.HostedDrawerState

@Composable
internal fun BoxScope.InlineDrawer(
    drawers: List<Pair<Drawer, HostedDrawerState>>,
    size: DpSize,
    content: @Composable () -> Unit
) {
    if (drawers.isEmpty()) return content()

    val candidate = drawers[0]
    val (drawer, state) = candidate
    val remaining = remember(drawers, drawer, state) { drawers - candidate }

    when (drawer.position) {
        DrawerPosition.Left -> Row(modifier = Modifier.fillMaxSize()) {
            val width by animateDpAsState(targetValue = state.dynamicSpan(size.width))
            if (width > 0.dp) Box(modifier = Modifier.width(width).fillMaxHeight(), content = drawer.content)
            Box(modifier = Modifier.width(size.width - width).fillMaxHeight()) { InlineDrawer(remaining, size, content) }
        }

        DrawerPosition.Right -> Row(modifier = Modifier.fillMaxSize()) {
            val width by animateDpAsState(targetValue = state.dynamicSpan(size.width))
            Box(modifier = Modifier.width(size.width - width).fillMaxHeight()) { InlineDrawer(remaining, size, content) }
            if (width > 0.dp) Box(modifier = Modifier.width(width).fillMaxHeight(), content = drawer.content)
        }

        DrawerPosition.Top -> Column(modifier = Modifier.fillMaxSize()) {
            val height by animateDpAsState(targetValue = state.dynamicSpan(size.height))
            if (height > 0.dp) Box(modifier = Modifier.height(height).fillMaxWidth(), content = drawer.content)
            Box(modifier = Modifier.height(size.height - height).fillMaxWidth()) { InlineDrawer(remaining, size, content) }
        }

        DrawerPosition.Bottom -> Column(modifier = Modifier.fillMaxSize()) {
            val height by animateDpAsState(targetValue = state.dynamicSpan(size.height))
            Box(modifier = Modifier.height(size.height - height).fillMaxWidth()) { InlineDrawer(remaining, size, content) }
            if (height > 0.dp) Box(modifier = Modifier.height(height).fillMaxWidth(), content = drawer.content)
        }
    }
}