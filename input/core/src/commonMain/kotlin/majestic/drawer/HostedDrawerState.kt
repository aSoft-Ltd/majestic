package majestic.drawer

internal sealed interface HostedDrawerState

internal data class OpenedDrawer(
    val span: DrawerSpan,
    val display: DrawerDisplay
) : HostedDrawerState

internal data object ClosedDrawer : HostedDrawerState