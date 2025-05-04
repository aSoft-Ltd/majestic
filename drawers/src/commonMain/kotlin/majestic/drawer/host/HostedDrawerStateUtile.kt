package majestic.drawer.host

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import majestic.drawer.ClosedDrawer
import majestic.drawer.DpSpan
import majestic.drawer.Drawer
import majestic.drawer.DrawerSpan
import majestic.drawer.HostedDrawerState
import majestic.drawer.OpenedDrawer
import majestic.drawer.RatioSpan

internal fun DrawerSpan.toDp(parent: Dp): Dp = when (this) {
    is DpSpan -> value
    is RatioSpan -> parent * value
}

@Composable
internal fun IntSize.toDp(): DpSize = with(LocalDensity.current) { DpSize(width.toDp(), height.toDp()) }

internal fun HostedDrawerState.computeSpan(parent: Dp) = when (this) {
    is ClosedDrawer -> 0.dp
    is OpenedDrawer -> span.toDp(parent)
}

internal fun HostedDrawerState.display(drawer: Drawer) = when (this) {
    is ClosedDrawer -> drawer.display
    is OpenedDrawer -> display
}