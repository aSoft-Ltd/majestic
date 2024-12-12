package majestic.drawer.host

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import majestic.drawer.ClosedDrawer
import majestic.drawer.Drawer
import majestic.drawer.DrawerPosition
import majestic.drawer.HostedDrawerState
import majestic.drawer.OpenedDrawer


@Composable
internal fun BoxScope.HorizontalOverlayDrawer(
    drawer: Drawer,
    size: DpSize,
    state: HostedDrawerState
) {
    val width by animateDpAsState(targetValue = state.fixedSpan(drawer, size.width))

    val offsetX by animateDpAsState(
        targetValue = when {
            drawer.position == DrawerPosition.Left && state is OpenedDrawer -> 0.dp
            drawer.position == DrawerPosition.Left && state is ClosedDrawer -> -width
            drawer.position == DrawerPosition.Right && state is OpenedDrawer -> size.width - width
            drawer.position == DrawerPosition.Right && state is ClosedDrawer -> size.width
            else -> throw IllegalStateException()
        },
    )
    Box(modifier = Modifier.width(width).offset(x = offsetX), content = drawer.content)
}