package majestic.drawer.host

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import majestic.drawer.ClosedDrawer
import majestic.drawer.Drawer
import majestic.drawer.DrawerPosition
import majestic.drawer.HostedDrawerState
import majestic.drawer.OpenedDrawer


@Composable
internal fun BoxScope.OverlayDrawer(
    drawer: Drawer,
    size: DpSize,
    state: HostedDrawerState
) = Box(modifier = Modifier.drawer(drawer, state, size), content = drawer.content)

@Composable
private fun Modifier.drawer(drawer: Drawer, state: HostedDrawerState, size: DpSize) = when {
    drawer.position in listOf(DrawerPosition.Left, DrawerPosition.Right) -> {
        val width by animateDpAsState(targetValue = state.dynamicSpan(size.width))
        val offsetX by drawer.offsetX(state, size, width)
        Modifier.width(width).offset(x = offsetX)
    }

    else -> {
        val height by animateDpAsState(targetValue = state.dynamicSpan(size.height))
        val offsetY by drawer.offsetY(state, size, height)
        Modifier.height(height).offset(y = offsetY)
    }
}

@Composable
private fun Drawer.offsetX(state: HostedDrawerState, size: DpSize, width: Dp) = animateDpAsState(
    targetValue = when {
        position == DrawerPosition.Left && state is OpenedDrawer -> 0.dp
        position == DrawerPosition.Left && state is ClosedDrawer -> -width
        position == DrawerPosition.Right && state is OpenedDrawer -> size.width - width
        position == DrawerPosition.Right && state is ClosedDrawer -> size.width
        else -> throw IllegalStateException()
    },
)

@Composable
private fun Drawer.offsetY(state: HostedDrawerState, size: DpSize, height: Dp) = animateDpAsState(
    targetValue = when {
        position == DrawerPosition.Top && state is OpenedDrawer -> 0.dp
        position == DrawerPosition.Top && state is ClosedDrawer -> -height
        position == DrawerPosition.Bottom && state is OpenedDrawer -> size.height - height
        position == DrawerPosition.Bottom && state is ClosedDrawer -> size.height
        else -> throw IllegalStateException()
    },
)